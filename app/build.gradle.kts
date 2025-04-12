plugins {
    alias(libs.plugins.lello.android.application)
    alias(libs.plugins.lello.android.application.compose.ui)
    alias(libs.plugins.lello.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.faening.lello"

    defaultConfig {
        applicationId = "io.github.faening.lello"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    api(projects.core.designsystem)
    api(projects.feature.home)
    api(projects.feature.diary)
    api(projects.feature.dashboard)
    api(projects.feature.profile)
}