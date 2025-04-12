plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.hilt)
}

android {
    namespace = "io.github.faening.lello.core.domain"
}

dependencies {
    api(projects.core.model)

    testImplementation(libs.junit)
}