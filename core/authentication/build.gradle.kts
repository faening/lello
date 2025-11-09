plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.firebase.authentication)
    alias(libs.plugins.lello.hilt)
}

android {
    namespace = "io.github.faening.lello.core.authentication"
}

dependencies {
    api(projects.core.domain)
    api(projects.core.model)
    api(projects.core.navigation)
    api(projects.feature.onboarding)
    api(projects.feature.home)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}