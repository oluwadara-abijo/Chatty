plugins {
    id("com.fueled.chatty.app")
}

android {
    namespace = "com.fueled.chatty"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:characters"))
    implementation(project(":feature:events"))

    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.navigation.compose)
}
