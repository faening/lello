import com.android.build.gradle.LibraryExtension
import io.github.faening.lello.buildlogic.configureAndroidComposeUI
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidLibraryComposeUIConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidComposeUI(this)
            }
        }
    }
}
