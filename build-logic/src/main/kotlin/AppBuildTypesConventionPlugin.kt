import com.android.build.api.dsl.ApplicationExtension
import com.fueled.chatty.build.logic.configureAndroidBuilds
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Configures BuildTypes for the application
 * - LOCAL
 * - DEBUG
 * - SNAPSHOT
 * - RELEASE
 */
class AppBuildTypesConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val applicationExtension = extensions.getByType<ApplicationExtension>()
            configureAndroidBuilds(applicationExtension)
        }
    }
}
