import io.github.faening.lello.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class FirebaseAuthenticationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", platform(libs.findLibrary("firebase-bom").get()))
                add("implementation", libs.findLibrary("firebase-authentication").get())
                add("implementation", libs.findLibrary("androidx-credentials").get())
                add("implementation", libs.findLibrary("androidx-credentials-play-service-auth").get())
                add("implementation", libs.findLibrary("google-id").get())
            }
        }
    }
}
