
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.fueled.chatty.build.logic.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import java.io.InvalidObjectException

/**
 * Configures and applies compose to the application module
 */
class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val extension = if (plugins.hasPlugin("com.android.application")) {
                extensions.getByType<ApplicationExtension>()
            } else if (plugins.hasPlugin("com.android.library")) {
                extensions.getByType<LibraryExtension>()
            } else {
                throw InvalidObjectException(
                    "Attempting to apply Jetpack Compose to a module that " +
                            "is neither an android app module or android library module."
                )
            }
            configureCompose(extension)
        }
    }
}
