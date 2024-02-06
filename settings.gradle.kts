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
include(":features:account")
include(":features:authorization")
include(":features:design:compose")
include(":features:di")
include(":features:feed")
include(":features:network")
include(":features:utils:decompose")
include(":features:utils:coroutines")
