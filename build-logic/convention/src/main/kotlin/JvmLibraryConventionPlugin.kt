import io.github.faening.lello.buildlogic.configureKotlinJvm
import io.github.faening.lello.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                // apply("java-library")
            }

            configureKotlinJvm()
            dependencies {
                add("testImplementation", libs.findLibrary("kotlin-test").get())
            }
        }
    }
}
