package com.fueled.chatty.build.logic

import com.android.build.api.dsl.CommonExtension
import com.fueled.chatty.build.logic.ext.getVersionCatalog
import com.fueled.chatty.build.logic.ext.library
import com.fueled.chatty.build.logic.ext.version
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    val versionCatalog = getVersionCatalog()

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                versionCatalog.version("androidxComposeCompiler")
        }

        dependencies {
            val bom = versionCatalog.library("androidx-compose-bom")
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
        }
    }
}
