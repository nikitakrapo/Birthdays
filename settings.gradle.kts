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

rootProject.name = "Birthdays"
include(":androidApp")
include(":shared")
include(":strings")
include(":features:authorization:api")
include(":features:authorization:firebase")
include(":features:authorization:ui-logic")
include(":features:authorization:compose-ui")
include(":features:experiments")
include(":features:authorization")
include(":features:design:compose")
include(":features:datechooser:ui-logic")
include(":features:datechooser:compose-ui")
include(":features:di")
include(":features:cloud-messaging")
include(":features:feed:ui-logic")
include(":features:feed:compose-ui")
include(":features:models")
include(":features:models-dto")
include(":features:repositories")
include(":features:network")
include(":features:profile:ui-logic")
include(":features:profile:compose-ui")
include(":features:wizard:ui-logic")
include(":features:wizard:compose-ui")
include(":features:utils:decompose")
include(":features:utils:coroutines")
include(":features:utils:platform")
