enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Trips"
include(":androidApp")
include(":shared")
include(":strings")
include(":features:feed")
include(":features:network")
include(":features:design:compose")
include(":features:utils:decompose")
