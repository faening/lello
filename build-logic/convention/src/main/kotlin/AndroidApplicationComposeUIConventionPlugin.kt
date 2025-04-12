import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import io.github.faening.lello.buildlogic.configureAndroidComposeUI
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeUIConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidComposeUI(extension)
        }
    }
}
