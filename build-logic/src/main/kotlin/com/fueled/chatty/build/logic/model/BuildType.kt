package com.fueled.chatty.build.logic.model

enum class BuildType(
    val packageNameSuffix: String?,
    val versionNameSuffix: String?,
    val isDebuggable: Boolean,
    val shrinkResources: Boolean,
    val isMinified: Boolean,
    val proguardFiles: Array<String>,
    val testCoverageEnabled: Boolean,
    val signingConfig: String,
) {
    LOCAL(
        packageNameSuffix = ".dev",
        versionNameSuffix = "-dev",
        isDebuggable = true,
        shrinkResources = false,
        isMinified = false,
        proguardFiles = arrayOf("proguard-android.txt", "proguard-rules.pro"),
        testCoverageEnabled = true,
        signingConfig = "debug",
    ),
    DEBUG(
        packageNameSuffix = ".dev",
        versionNameSuffix = "-dev",
        isDebuggable = true,
        shrinkResources = false,
        isMinified = false,
        proguardFiles = arrayOf("proguard-android.txt", "proguard-rules.pro"),
        testCoverageEnabled = true,
        signingConfig = "debug",
    ),
    SNAPSHOT(
        packageNameSuffix = ".snapshot",
        versionNameSuffix = "-snapshot",
        isDebuggable = true,
        shrinkResources = true,
        isMinified = true,
        proguardFiles = arrayOf("proguard-android.txt", "proguard-rules.pro"),
        testCoverageEnabled = true,
        signingConfig = "release",
    ),
    RELEASE(
        packageNameSuffix = null,
        versionNameSuffix = null,
        isDebuggable = false,
        shrinkResources = true,
        isMinified = true,
        proguardFiles = arrayOf(
            "proguard-android-optimize.txt",
            "proguard-rules.pro",
            "proguard-rules-release.pro"
        ),
        testCoverageEnabled = false,
        signingConfig = "release",
    )
}
