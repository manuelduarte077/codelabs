val javaVersion: String = System.getProperty("java.version")
if (javaVersion.substringBefore(".").toInt() < 11) {
    throw GradleException("Java 11 or higher is required to build this project. You are using Java $javaVersion.")
}

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

rootProject.name = "GraphQlCountries"
include(":app")
