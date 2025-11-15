import io.github.faening.lello.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class WorkManagerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                pluginManager.withPlugin("com.android.base") {
                    add("implementation", libs.findLibrary("androidx-work").get())
                    add("implementation", libs.findLibrary("androidx-work-gcm").get())
                    add("implementation", libs.findLibrary("androidx-work-multiprocess").get())
                    add("androidTestImplementation", libs.findLibrary("androidx-work-test").get())
                }
            }
        }
    }
}
