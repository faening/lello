import com.android.build.api.dsl.ApplicationExtension
import io.github.faening.lello.buildlogic.configureAndroidComposeUI
import io.github.faening.lello.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidApplicationComposeUIConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            dependencies {
                add("implementation", libs.findLibrary("accompanist-systemuicontroller").get())
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidComposeUI(this)
            }
        }
    }
}
