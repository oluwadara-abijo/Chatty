import com.fueled.chatty.build.logic.configureJacoco
import com.fueled.chatty.build.logic.ext.getVersionCatalog
import com.fueled.chatty.build.logic.ext.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

/**
 * Configures and applies unit test code coverage to the module.
 */
class TestCoverageConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.gradle.jacoco")

            val jacocoExtension = extensions.getByType<JacocoPluginExtension>()
            val versionCatalog = getVersionCatalog()
            jacocoExtension.apply {
                toolVersion = versionCatalog.version("jacoco")
            }

            configureJacoco(
                instruction = 80.0,
                branch = 80.0
            )
        }
    }
}
