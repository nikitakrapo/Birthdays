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
                implementation(projects.features.network)
                implementation(projects.features.di)
                implementation(projects.features.utils.platform)
                implementation(projects.features.utils.coroutines)
                implementation(libs.napier)
                implementation(libs.multiplatformSettings)
                implementation(libs.kotlin.serialization)
                implementation(libs.ktor.contentNegotiation.json)
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.cms"
    moduleConfigurationPlugin.configureAndroidDefaults()
}
