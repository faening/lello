
import com.android.build.gradle.LibraryExtension
import io.github.faening.lello.buildlogic.configureLibraryBuildTypes
import io.github.faening.lello.buildlogic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureLibraryBuildTypes(this)
                // configureFlavors(this)
                // configureApplicationBuildTypes(this)
                defaultConfig.targetSdk = 35
            }
        }
    }
}