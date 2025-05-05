plugins {
    alias(libs.plugins.lello.android.library)
    alias(libs.plugins.lello.android.room)
    alias(libs.plugins.lello.hilt)
}

android {
    namespace = "io.github.faening.lello.core.database"
}

dependencies {
    api(projects.core.model)
    api(projects.core.domain)

    implementation(libs.kotlinx.datetime)
}