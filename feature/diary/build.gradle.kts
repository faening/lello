plugins {
    alias(libs.plugins.lello.android.feature)
    alias(libs.plugins.lello.android.library.compose.ui)
}

android {
    namespace = "io.github.faening.lello.feature.diary"
}

dependencies {
    api(projects.core.domain)
    api(projects.core.model)
    api(projects.core.navigation)
}