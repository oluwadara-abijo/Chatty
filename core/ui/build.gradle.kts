plugins {
    id("com.fueled.chatty.core")
    id("com.fueled.chatty.compose")
}

android {
    namespace = "com.fueled.chatty.core.ui"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.accompanist.systemuicontroller)

    api(libs.androidx.hilt.navigation.compose)

    api(libs.bundles.compose.core)

    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)

    api(libs.bundles.coil)
}
