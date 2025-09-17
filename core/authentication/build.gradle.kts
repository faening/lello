plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.hilt)
}

android {
    namespace = "io.github.faening.lello.core.authentication"
}

dependencies {
    api(projects.core.domain)
    api(projects.core.model)
    api(projects.feature.onboarding)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.authentication)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}