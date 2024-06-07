plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    id("birthdays.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlin.datetime)
            }
        }
    }
}

android {
    moduleConfigurationPlugin.configureAndroidDefaults()
    namespace = "com.nikitakrapo.birthdays.models"
}
