plugins {
    alias(libs.plugins.lello.android.application)
    alias(libs.plugins.lello.android.application.compose.ui)
    alias(libs.plugins.lello.firebase.authentication)
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
    api(projects.core.data)
    api(projects.core.designsystem)
    api(projects.core.domain)
    api(projects.feature.home)
    api(projects.feature.diary)
    api(projects.feature.authentication)
    api(projects.feature.onboarding)
    api(projects.feature.achievement)
    api(projects.feature.medication)
    api(projects.feature.profile)
    api(projects.feature.settings)
    api(projects.feature.journal.meal)
    api(projects.feature.journal.medication)
    api(projects.feature.journal.mood)
    api(projects.feature.journal.sleep)
    api(projects.feature.journal.settings)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
}