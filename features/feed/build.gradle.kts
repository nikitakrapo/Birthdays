plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    id("trips.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.repositories)
                implementation(projects.features.di)
                implementation(libs.decompose)
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
    namespace = "com.nikitakrapo.trips.feed"
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
