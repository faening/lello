plugins {
    alias(libs.plugins.lello.android.feature)
    alias(libs.plugins.lello.android.library.compose.ui)
}

android {
    namespace = "io.github.faening.lello.core.notification"
}

dependencies {
    api(projects.core.designsystem)
    api(projects.core.domain)
    api(projects.core.model)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}