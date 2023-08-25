import org.gradle.api.JavaVersion.VERSION_17

plugins {
    `kotlin-dsl`
}

group = "com.fueled.chatty.build.logic"

java {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
}

dependencies {
    // Gradle Plugins required by the build script for each of the Convention Plugins.
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = "com.fueled.chatty.app"
            implementationClass = "AppConventionPlugin"
        }
        register("androidAppCompose") {
            id = "com.fueled.chatty.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("androidAppBuildTypes") {
            id = "com.fueled.chatty.app.build.types"
            implementationClass = "AppBuildTypesConventionPlugin"
        }
        register("androidLibraryBuildTypes") {
            id = "com.fueled.chatty.library.build.types"
            implementationClass = "LibraryBuildTypesConventionPlugin"
        }
        register("androidCore") {
            id = "com.fueled.chatty.core"
            implementationClass = "CoreConventionPlugin"
        }
        register("androidFeature") {
            id = "com.fueled.chatty.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.fueled.chatty.library.compose"
            implementationClass = "LibraryComposeConventionPlugin"
        }
        register("spotless") {
            id = "com.fueled.chatty.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("hilt") {
            id = "com.fueled.chatty.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("detekt") {
            id = "com.fueled.chatty.detekt"
            implementationClass = "DetektConventionPlugin"
        }
        register("testCoverage") {
            id = "com.fueled.chatty.test.coverage"
            implementationClass = "TestCoverageConventionPlugin"
        }
    }
}
