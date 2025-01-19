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

rootProject.name = "OffLineMusic"
include(":app")
include(":core")
include(":feature")
include(":core:data")
include(":core:domain")
include(":feature:music_player")
include(":feature:media_service")

include(":core:common")
include(":core:resource")
include(":feature:home")
include(":feature:search")
include(":feature:playlist")
include(":app-test")
include(":core:designsystem")
include(":feature:lyrics")
