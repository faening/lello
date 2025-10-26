plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.android.library.compose.ui)
}

android {
    namespace = "io.github.faening.lello.core.designsystem"
}

dependencies {
    api(projects.core.model)
    api(projects.core.testing)
    implementation(libs.androidx.core.ktx)
}