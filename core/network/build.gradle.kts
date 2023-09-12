plugins {
    id("com.fueled.chatty.core")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.fueled.chatty.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:common"))
    api(libs.androidx.core.ktx)

    api(libs.gson)

    api(libs.okhttp.logging.interceptor)

    api(libs.moshi.core)
    ksp(libs.moshi.codegen)
}
