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

@Suppress("UnstableApiUsage")
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
include(":core:audio")
include(":core:authentication")
include(":core:data")
include(":core:database")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":core:navigation")
include(":core:notification")
include(":core:testing")
include(":feature:achievement")
include(":feature:authentication")
include(":feature:diary")
include(":feature:home")
include(":feature:journal:meal")
include(":feature:journal:medication")
include(":feature:journal:mood")
include(":feature:journal:sleep")
include(":feature:journal:settings")
include(":feature:medication")
include(":feature:onboarding")
include(":feature:settings")