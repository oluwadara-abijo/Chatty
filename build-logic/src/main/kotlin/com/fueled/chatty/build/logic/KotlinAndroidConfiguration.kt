package com.fueled.chatty.build.logic

import com.android.build.api.dsl.CommonExtension
import com.fueled.chatty.build.logic.ext.getVersionCatalog
import com.fueled.chatty.build.logic.ext.kotlinOptions
import com.fueled.chatty.build.logic.ext.library
import com.fueled.chatty.build.logic.model.DefaultBuildConfiguration
import org.gradle.api.JavaVersion.VERSION_11
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = DefaultBuildConfiguration.COMPILE_SDK

        defaultConfig {
            minSdk = DefaultBuildConfiguration.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = VERSION_11
            targetCompatibility = VERSION_11
        }

        kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.Experimental",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=androidx.compose.ui.text.ExperimentalTextApi"
            )

            jvmTarget = VERSION_11.toString()
        }
    }

    val versionCatalog = getVersionCatalog()

    dependencies {
        add("coreLibraryDesugaring", versionCatalog.library("android.desugarJdkLibs"))
    }
}
