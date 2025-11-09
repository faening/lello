plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.android.library.compose.ui)
}

android {
    namespace = "io.github.faening.lello.core.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}