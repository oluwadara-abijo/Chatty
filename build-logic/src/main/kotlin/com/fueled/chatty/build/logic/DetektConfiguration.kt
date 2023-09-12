package com.fueled.chatty.build.logic

import com.fueled.chatty.build.logic.model.BuildConstants
import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.task
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

internal fun Project.registerIncrementalDetektTask(version: String) =
    task<JavaExec>("incrementalDetekt") {
        group = "CI Detekt Checks"
        description = "Run Kotlin static analysis on changed files."
        classpath = configurations["detekt"]
        mainClass.set("io.gitlab.arturbosch.detekt.cli.Main")
        val formattingPluginFile = File(rootProject.buildDir, "detekt-formatting.jar")
        val customRulesModuleName = "detekt-rules"
        // We need to build a jar since detekt cli only works on plugin jars
        dependsOn(":$customRulesModuleName:jar")
        val customPlugin = File(
            rootProject.projectDir,
            "$customRulesModuleName/build/libs/$customRulesModuleName.jar"
        )
        val rulesLibrariesPluginFile = File(
            rootProject.projectDir,
            "$customRulesModuleName/build/libs/detekt-rules-libraries.jar"
        )
        doFirst {
            var (changedFiles, count) = getDiffedFilesFromBranch(
                branch = BuildConstants.baseBranch,
                project = project
            )
            if (count == 0) {
                println("No kotlin files changed! Skipping task...")
                // forces detekt to ignore all files
                changedFiles = "$rootDir/gradle"
            } else {
                // Download the formatter plugin
                // https://github.com/detekt/detekt/discussions/4790
                if (!formattingPluginFile.exists()) {
                    downloadDetektFile(
                        detektVersion = version,
                        fileName = "detekt-formatting",
                        targetFile = formattingPluginFile,
                    )
                }
                if (!rulesLibrariesPluginFile.exists()) {
                    downloadDetektFile(
                        detektVersion = version,
                        fileName = "detekt-rules-libraries",
                        targetFile = rulesLibrariesPluginFile,
                    )
                }
                println("Running detekt on $count file(s)")
            }

            val params = listOf(
                "-i",
                changedFiles,
                "-c",
                "$rootDir/detekt/config/detekt.yml",
                "-p",
                "${formattingPluginFile.absolutePath};${customPlugin.absolutePath};" +
                        rulesLibrariesPluginFile.absolutePath
            )
            args(params)
        }
    }

private fun downloadDetektFile(detektVersion: String, fileName: String, targetFile: File) {
    val url = "https://github.com/detekt/detekt/releases/download/v" +
            "$detektVersion/$fileName-$detektVersion.jar"
    targetFile.parentFile.mkdirs()
    println("Downloading file : $targetFile")
    URL(url).openStream().use {
        Files.copy(
            it,
            Paths.get(targetFile.absolutePath),
            REPLACE_EXISTING
        )
    }
}

private fun getDiffedFilesFromBranch(branch: String, project: Project): Pair<String, Int> {
    val outputStream = ByteArrayOutputStream()
    val path = project.rootDir.absolutePath
    val cmd = "cd $path; git diff --diff-filter=d --name-only origin/$branch --relative"
    project.exec {
        executable = "sh"
        standardOutput = outputStream
        args = listOf("-c", cmd)
    }
    val output = outputStream.toString().trim()
    if (output.isEmpty()) return "" to 0
    // get comma separated string of files
    var fileCount: Int
    return output
        .split("\n")
        // Filter kotlin files
        .filter { it.matches(Regex(".*(\\.kt)(s)*")) }
        .also {
            fileCount = it.size
        }
        .joinToString(",") {
            "$path/$it"
        } to fileCount
}
