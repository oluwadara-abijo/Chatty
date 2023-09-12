package com.fueled.chatty.build.logic

import com.android.build.gradle.BaseExtension
import groovy.xml.XmlSlurper
import groovy.xml.slurpersupport.NodeChild
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File
import kotlin.math.roundToInt

/**
 * @param instruction The percent coverage limit for instruction
 * @param branch The percent coverage limit for branch
 */
internal fun Project.configureJacoco(instruction: Double, branch: Double) {
    limits["instruction"] = instruction
    limits["branch"] = branch
    afterEvaluate {
        val buildTypes = android.buildTypes.map { buildType -> buildType.name }
        var productFlavors = android.productFlavors.map { flavor -> flavor.name }

        if (productFlavors.isEmpty()) {
            productFlavors = productFlavors + ""
        }

        productFlavors.forEach { flavor ->
            buildTypes.forEach { buildType ->
                val sourceName: String
                val sourcePath: String

                if (flavor.isEmpty()) {
                    sourceName = buildType
                    sourcePath = buildType
                } else {
                    sourceName = "$flavor${buildType.replaceFirstChar { it.uppercase() }}"
                    sourcePath = "$flavor/$buildType"
                }

                val testTaskName = "test${sourceName.replaceFirstChar { it.uppercase() }}UnitTest"

                registerCodeCoverageTask(
                    testTaskName = testTaskName,
                    sourceName = sourceName,
                    sourcePath = sourcePath,
                    flavorName = flavor,
                    buildTypeName = buildType,
                )
            }
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        configure<JacocoTaskExtension> {
            isIncludeNoLocationClasses = true
            excludes = listOf("jdk.internal.*")
        }
    }
}

private fun Project.registerCodeCoverageTask(
    testTaskName: String,
    sourceName: String,
    sourcePath: String,
    flavorName: String,
    buildTypeName: String,
) {
    tasks.register<JacocoReport>("${testTaskName}Coverage") {
        dependsOn(testTaskName)
        group = "Reporting"
        description =
            "Generate Jacoco coverage reports on the ${sourceName.replaceFirstChar { it.uppercase() }} build."

        val javaDirectories = fileTree(
            "${project.buildDir}/intermediates/classes/$sourcePath"
        ) { exclude(excludedFiles) }

        val kotlinDirectories = fileTree(
            "${project.buildDir}/tmp/kotlin-classes/$sourcePath"
        ) { exclude(excludedFiles) }

        val coverageSrcDirectories = listOf(
            "src/main/java",
            "src/main/kotlin",
            "src/$flavorName/java",
            "src/$flavorName/kotlin",
            "src/$buildTypeName/java",
            "src/$buildTypeName/kotlin",
        )

        classDirectories.setFrom(files(javaDirectories, kotlinDirectories))
        additionalClassDirs.setFrom(files(coverageSrcDirectories))
        sourceDirectories.setFrom(files(coverageSrcDirectories))
        executionData.setFrom(
            files("${project.buildDir}/jacoco/$testTaskName.exec")
        )

        reports {
            xml.required.set(true)
            html.required.set(true)
        }

        doLast {
            jacocoTestReport("${testTaskName}Coverage")
        }
    }
}

/**
 *  Checks Jacoco coverage results inside the xml file.
 *  Logs the results if coverage is successful or not
 */
private fun Project.jacocoTestReport(testTaskName: String) {
    val reportsDirectory = jacoco.reportsDirectory.asFile.get()
    val report = file("$reportsDirectory/$testTaskName/$testTaskName.xml")
    val htmlReport = "$reportsDirectory/$testTaskName/html/index.html"
    val (isFailure, outputString) = generateCoverageResult(report, htmlReport)
    if (isFailure) {
        throw GradleException(outputString)
    } else {
        logger.quiet(outputString)
    }
}

/**
 * Returns a pair of <Boolean, String> for the test success result and the string to be printed
 * to the logger with the coverage results
 */
private fun generateCoverageResult(
    report: File,
    htmlReportPath: String,
): Pair<Boolean, String> {
    var isFailure = false
    val outputString = buildString {
        val results = report.extractTestsCoveredByType().map { entry ->
            val isMetricSuccess = entry.value >= limits[entry.key]!!
            val emoji = if (isMetricSuccess) "✅" else "❌"
            if (!isMetricSuccess) {
                isFailure = true
            }
            "- $emoji ${entry.key} coverage rate is: ${entry.value}%, minimum is ${limits[entry.key]}%"
        }

        if (isFailure) {
            appendLine("\uD83D\uDCA5 Oh Snap! \uD83D\uDE13 Unit test code coverage has failed")
            appendLine(DIVIDING_LINE)
        } else {
            appendLine("\uD83D\uDC4C Nice work! \uD83C\uDF89 Test coverage has succeeded!")
            appendLine(DIVIDING_LINE)
        }
        results.forEach { result -> appendLine(result) }
        appendLine(DIVIDING_LINE)
        appendLine("Check out the report here -> $htmlReportPath")
        appendLine(DIVIDING_LINE)
    }
    return Pair(isFailure, outputString)
}

/**
 *  Extracts tests covered in percentage by type.
 *  Sample output:
 *  ["instruction" : 85.32]
 *  ["branch" : 45.94]
 *  ["line" : 25.82]
 *  ["complexity" : 95.21]
 *  ["method" : 100.00]
 *  ["class" : 12.89]
 */
@Suppress("UNCHECKED_CAST")
private fun File.extractTestsCoveredByType(): Map<String, Double> {
    val xmlReader = XmlSlurper().apply {
        setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    }

    val counterNodes: List<NodeChild> = xmlReader.parse(this)
        .parent()
        .children()
        .filter { (it as NodeChild).name() == "counter" } as List<NodeChild>

    return counterNodes.associate { nodeChild ->
        val type = nodeChild.attributes()["type"].toString().lowercase()
        val covered = nodeChild.attributes()["covered"].toString().toDouble()
        val missed = nodeChild.attributes()["missed"].toString().toDouble()
        val percentage = ((covered / (covered + missed)) * MULTIPLIER).roundToInt() / DIVIDER
        type to percentage
    }
}

private const val DIVIDING_LINE = "-----------------------------------------------------------------------"
private const val MULTIPLIER = 10_000.0
private const val DIVIDER = 100.0

private val Project.android: BaseExtension
    get() = extensions.findByName("android") as BaseExtension

private val Project.jacoco: JacocoPluginExtension
    get() = extensions.findByName("jacoco") as JacocoPluginExtension

private val limits = mutableMapOf(
    "instruction" to 0.0,
    "branch" to 0.0,
    "line" to 0.0,
    "complexity" to 0.0,
    "method" to 0.0,
    "class" to 0.0
)

private val excludedFiles = mutableSetOf(
    "**/BuildConfig.class",
    "src/main/gen/**/*",
    "src/main/assets/**/*",
    "**/R*.class",
    "**/*_MembersInjector.class",
    "**/Dagger*Component.class",
    "**/*Module_*Factory.class",
    "**/*Hilt*.*",
    "**/di/*Module.*",
    "**/data/**",
    "**/di/**",
    "**/navigation/**",
    "**/model/**",
    "**/components/**",
    "**/*ScreenKt*",
)
