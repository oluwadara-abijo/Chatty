
import com.android.build.gradle.LibraryExtension
import com.fueled.chatty.build.logic.configureKotlinAndroid
import com.fueled.chatty.build.logic.model.DefaultBuildConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Configures Kotlin and Android Library plugin to the gradle project.
 */
class CoreConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
                apply("com.fueled.chatty.library.build.types")
                apply("com.fueled.chatty.detekt")
                apply("com.fueled.chatty.spotless")
                apply("com.fueled.chatty.hilt")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = DefaultBuildConfiguration.TARGET_SDK
                configureKotlinAndroid(this)
            }
        }
    }
}
