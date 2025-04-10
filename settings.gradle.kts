pluginManagement {
     includeBuild("build-logic")
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:authentication")
include(":core:database")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":feature:home")
include(":feature:diary")
include(":feature:dashboard")
include(":feature:profile")
include(":feature:diarymeal")
include(":feature:diarymedication")
include(":feature:diarymood")
include(":feature:diarysleep")