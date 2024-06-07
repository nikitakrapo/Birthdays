plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
    id("birthdays.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.di)
                implementation(projects.features.experiments)
                implementation(projects.features.utils.decompose)
                implementation(libs.decompose)
                implementation(libs.kotlin.datetime)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.decompose.extensions.compose)
                implementation(projects.strings)
                implementation(projects.features.design.compose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.datechooser"
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
