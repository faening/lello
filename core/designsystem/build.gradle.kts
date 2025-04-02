plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
}

android {
    namespace = "io.github.faening.lello.core.designsystem"
}

dependencies {
    // Jetpack Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.navigationSuite)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(platform(libs.androidx.compose.bom))
}