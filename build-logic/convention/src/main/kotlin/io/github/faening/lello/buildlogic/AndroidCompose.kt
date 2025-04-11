package io.github.faening.lello.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.15" // Compose UI
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            "implementation"(platform(bom))
            "implementation"(libs.findLibrary("androidx-compose-runtime").get())
            "implementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            "implementation"(libs.findLibrary("androidx-compose-ui-tooling").get())

            "androidTestImplementation"(platform(bom))
        }

//        testOptions {
//            unitTests {
//                // For Robolectric
//                // isIncludeAndroidResources = true
//            }
//        }
    }
}
