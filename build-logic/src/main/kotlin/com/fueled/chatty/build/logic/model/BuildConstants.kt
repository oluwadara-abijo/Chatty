package com.fueled.chatty.build.logic.model

object BuildConstants {

    val baseBranch = System.getenv("BASE_BRANCH") ?: "main"

    const val DETEKT_PLUGINS = "detektPlugins"
    const val IMPLEMENTATION = "implementation"
    const val KAPT = "kapt"
    const val KSP = "ksp"

    const val TEST_IMPLEMENTATION = "testImplementation"
    const val TEST_RUNTIME = "testRuntimeOnly"
}
