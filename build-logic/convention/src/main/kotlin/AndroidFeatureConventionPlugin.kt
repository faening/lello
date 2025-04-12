import com.android.build.gradle.LibraryExtension
import io.github.faening.lello.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("lello.android.library")
                apply("lello.hilt")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
                // configureGradleManagedDevices(this)
            }

            dependencies {
                add("implementation", project(":core:designsystem"))

                add("implementation", libs.findLibrary("androidx-navigation-compose").get())

                add("implementation", libs.findLibrary("androidx-lifecycle-runtime").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())

                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())

                add("testImplementation", libs.findLibrary("androidx-navigation-test").get())
                add("androidTestImplementation", libs.findLibrary("androidx-lifecycle-runtime-test").get())
            }
        }
    }
}
