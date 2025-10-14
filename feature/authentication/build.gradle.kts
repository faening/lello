plugins {
    alias(libs.plugins.lello.android.feature)
    alias(libs.plugins.lello.firebase.authentication)
    alias(libs.plugins.lello.android.library.compose.ui)
}
android {
    namespace = "io.github.faening.lello.feature.authentication"
}

dependencies {
    api(projects.core.authentication)
    api(projects.core.domain)
    api(projects.core.model)
}