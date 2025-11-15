import io.github.faening.lello.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                // Dependências aplicadas a TODOS os módulos Hilt (Android ou não)
                add("ksp", libs.findLibrary("hilt-compiler").get())
            }

            pluginManager.withPlugin("com.android.base") {
                dependencies {
                    add("implementation", libs.findLibrary("hilt-android").get())
                    add("implementation", libs.findLibrary("hilt-common").get())

                    add("implementation", libs.findLibrary("hilt-work").get())
                    add("ksp", libs.findLibrary("hilt-ext-compiler").get())

                    add("implementation", libs.findLibrary("androidx-navigation-compose-hilt").get())
                }
            }
        }
    }
}