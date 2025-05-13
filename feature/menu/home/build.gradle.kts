plugins {
    alias(libs.plugins.lello.android.feature)
    alias(libs.plugins.lello.android.library.compose.ui)
}

android {
    namespace = "io.github.faening.lello.feature.menu.home"
}

dependencies {
    api(projects.core.domain)
    api(projects.core.model)

    api(projects.feature.journal.meal)
    api(projects.feature.journal.medication)
    api(projects.feature.journal.mood)
    api(projects.feature.journal.sleep)
}