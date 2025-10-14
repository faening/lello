import com.android.build.gradle.LibraryExtension
import io.github.faening.lello.buildlogic.configureAndroidComposeUI
import io.github.faening.lello.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidLibraryComposeUIConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            dependencies {
                add("implementation", libs.findLibrary("accompanist-systemuicontroller").get())
            }

            extensions.configure<LibraryExtension> {
                configureAndroidComposeUI(this)
            }
        }
    }
}
