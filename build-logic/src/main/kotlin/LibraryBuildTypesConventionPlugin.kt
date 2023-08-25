import com.android.build.api.dsl.LibraryExtension
import com.fueled.chatty.build.logic.createBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Configures BuildTypes for the library module:
 * - LOCAL
 * - DEBUG
 * - SNAPSHOT
 * - RELEASE
 */
class LibraryBuildTypesConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libraryExtension = extensions.getByType<LibraryExtension>()
            libraryExtension.createBuildTypes()
        }
    }
}
