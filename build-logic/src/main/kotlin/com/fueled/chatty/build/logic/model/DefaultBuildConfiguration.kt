package com.fueled.chatty.build.logic.model

object DefaultBuildConfiguration {

    const val PACKAGE_NAME: String = "com.fueled.chatty.template"
    const val TARGET_SDK: Int = 33
    const val COMPILE_SDK: Int = 34
    const val MIN_SDK: Int = 26
    val versionCode: Int
        get() = System.getenv("FUELED_BUILD_NUMBER")?.toInt() ?: 1
    val versionName: String
        get() = System.getenv("BUILD_VERSION_NAME") ?: "0.1.0"
}
