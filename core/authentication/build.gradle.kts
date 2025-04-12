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

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.authentication)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}