@file:Suppress("unused")

package io.github.faening.lello.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
            buildConfig = true
        }

        dependencies {
            add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
            add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))

            add("implementation", libs.findLibrary("androidx-compose-runtime").get())

            add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        }

//        testOptions {
//            unitTests {
//                // Robolectric
//                isIncludeAndroidResources = true
//            }
//        }
    }
}

internal fun Project.configureAndroidComposeUI(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
            buildConfig = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.15"
        }

        dependencies {
            add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
            add("implementation", libs.findLibrary("androidx-compose-runtime").get())
            add("implementation", libs.findLibrary("androidx-compose-foundation").get())
            add("implementation", libs.findLibrary("androidx-compose-foundation-layout").get())
            add("implementation", libs.findLibrary("material").get())
            add("implementation", libs.findLibrary("androidx-compose-material-icons-core").get())
            add("implementation", libs.findLibrary("androidx-compose-material-icons-extended").get())
            add("implementation", libs.findLibrary("androidx-compose-material3").get())
            add("implementation", libs.findLibrary("androidx-compose-material3-adaptive-layout").get())
            add("implementation", libs.findLibrary("androidx-compose-material3-navigation-suite").get())
            add("implementation", libs.findLibrary("androidx-compose-material3-window-sizeclass").get())
            add("implementation", libs.findLibrary("androidx-compose-media3-exoplayer").get())
            add("implementation", libs.findLibrary("androidx-compose-media3-ui").get())
            add("implementation", libs.findLibrary("androidx-compose-ui").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-graphics").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-util").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
            add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))
            add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test").get())
            add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
        }
    }
}