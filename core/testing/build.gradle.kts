plugins {
    id("com.fueled.chatty.core")
}

android {
    namespace = "com.fueled.chatty.core.test"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.junit.jupiter)
    runtimeOnly(libs.junit.jupiter.engine)

    implementation(libs.kotlinx.coroutines.test)
}
