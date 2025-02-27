
import com.fueled.chatty.build.logic.ext.getVersionCatalog
import com.fueled.chatty.build.logic.ext.library
import com.fueled.chatty.build.logic.model.BuildConstants.IMPLEMENTATION
import com.fueled.chatty.build.logic.model.BuildConstants.KAPT
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/**
 * Configures and applies HILT on the gradle project.
 */
class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("kotlin-kapt")
            }

            val kaptExtension = extensions.getByType<KaptExtension>()
            kaptExtension.correctErrorTypes = true

            val versionCatalog = getVersionCatalog()
            dependencies {
                add(IMPLEMENTATION, versionCatalog.library("hilt.android"))
                add(KAPT, versionCatalog.library("hilt.compiler"))
            }
        }
    }
}
