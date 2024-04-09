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
include(":features:models")
include(":features:models-dto")
include(":features:repositories")
include(":features:network")
include(":features:profile")
include(":features:utils:decompose")
include(":features:utils:coroutines")
