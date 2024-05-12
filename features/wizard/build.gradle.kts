plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
    id("trips.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.di)
                implementation(projects.features.utils.decompose)
                implementation(libs.decompose)
                implementation(libs.kotlin.datetime)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.strings)
                implementation(projects.features.design.compose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.wizard"
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
