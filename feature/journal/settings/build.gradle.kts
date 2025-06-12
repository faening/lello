plugins {
    alias(libs.plugins.lello.android.feature)
    alias(libs.plugins.lello.android.library.compose.ui)
}

android {
    namespace = "io.github.faening.lello.feature.journal.settings"
}

dependencies {
    api(projects.core.domain)
    api(projects.core.model)
}