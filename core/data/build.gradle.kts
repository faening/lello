plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.faening.lello.core.data"
}

dependencies {
    api(projects.core.database)
    api(projects.core.domain)
    api(projects.core.model)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.junit)
}