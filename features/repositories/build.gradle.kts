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
                api(projects.features.modelsDto)
                api(projects.features.network)
                api(projects.features.di)
                api(projects.features.utils.coroutines)
                implementation(libs.kotlin.serialization)
            }
        }
    }
}

android {
    moduleConfigurationPlugin.configureAndroidDefaults()
    namespace = "com.nikitakrapo.trips.repositories"
}
