plugins {
    alias(libs.plugins.lello.android.feature)
    alias(libs.plugins.lello.android.library.compose.ui)
}

android {
    namespace = "io.github.faening.lello.feature.menu.diary"
}

dependencies {
    api(projects.core.data)

    api(projects.core.domain)
    api(projects.core.model)
}