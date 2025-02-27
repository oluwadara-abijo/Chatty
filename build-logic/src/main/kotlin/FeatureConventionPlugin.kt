import com.fueled.chatty.build.logic.ext.getVersionCatalog
import com.fueled.chatty.build.logic.ext.library
import com.fueled.chatty.build.logic.model.BuildConstants.IMPLEMENTATION
import com.fueled.chatty.build.logic.model.BuildConstants.KSP
import com.fueled.chatty.build.logic.model.BuildConstants.TEST_IMPLEMENTATION
import com.fueled.chatty.build.logic.model.BuildConstants.TEST_RUNTIME
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Adds core dependencies that a typical Feature module will require,
 */
class FeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.fueled.chatty.core")
                apply("com.fueled.chatty.compose")
                apply("com.fueled.chatty.test.coverage")
                apply("com.google.devtools.ksp")
            }

            val versionCatalog = getVersionCatalog()

            dependencies {
                add(IMPLEMENTATION, project(":core:common"))
                add(IMPLEMENTATION, project(":core:network"))
                add(IMPLEMENTATION, project(":core:ui"))
                add(IMPLEMENTATION, project(":core:testing"))

                add(KSP, versionCatalog.library("moshi.codegen"))

                add(TEST_IMPLEMENTATION, versionCatalog.library("junit.jupiter"))
                add(TEST_RUNTIME, versionCatalog.library("junit.jupiter.engine"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("mockk"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("androidx.arch.core.testing"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("kotlinx.coroutines.test"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("kotest.assertions.core"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("turbine"))
            }
        }
    }
}
