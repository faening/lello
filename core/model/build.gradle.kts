plugins {
    alias(libs.plugins.lello.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.datetime)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
}