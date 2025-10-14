plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.faening.lello.core.audio"
}

dependencies {
    api(projects.core.domain)
}