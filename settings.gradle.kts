pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "lello"

include(":app")
include(":core:authentication")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":feature:diarymeal")
include(":feature:diarymedication")
include(":feature:diarymood")
include(":feature:diarysleep")
