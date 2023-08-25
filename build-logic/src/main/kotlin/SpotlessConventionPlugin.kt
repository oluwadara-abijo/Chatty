import com.fueled.chatty.build.logic.configureSpotless
import com.fueled.chatty.build.logic.ext.getVersionCatalog
import com.fueled.chatty.build.logic.ext.version
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Configures and applies Spotless linting tool to the gradle project
 */
class SpotlessConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

            val versionCatalog = getVersionCatalog()
            val ktLintVersion = versionCatalog.version("ktlint")

            configureSpotless(ktLintVersion)
        }
    }
}
