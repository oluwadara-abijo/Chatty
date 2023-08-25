import com.fueled.chatty.build.logic.ext.getVersionCatalog
import com.fueled.chatty.build.logic.ext.library
import com.fueled.chatty.build.logic.ext.version
import com.fueled.chatty.build.logic.model.BuildConstants.DETEKT_PLUGINS
import com.fueled.chatty.build.logic.registerIncrementalDetektTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Configures and applies Detekt on the gradle project.
 */
class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.getByType<DetektExtension>().apply {
                config = files("$rootDir/detekt/config/detekt.yml")
            }

            val versionCatalog = getVersionCatalog()
            dependencies {
                add(DETEKT_PLUGINS, versionCatalog.library("detekt.formatting"))
                add(DETEKT_PLUGINS, versionCatalog.library("detekt.rules.libraries"))
                add(DETEKT_PLUGINS, project(":detekt-rules"))
            }
            registerIncrementalDetektTask(versionCatalog.version("detekt"))
        }
    }
}
