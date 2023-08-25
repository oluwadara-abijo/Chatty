plugins {
    id("com.fueled.chatty.core")
}

android {
    namespace = "com.fueled.chatty.core.common"
}

dependencies {
    api(libs.androidx.core.ktx)
    api(libs.bundles.lifecycle.ktx)

    api(libs.timber)
}
