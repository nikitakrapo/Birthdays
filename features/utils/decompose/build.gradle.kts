plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    id("birthdays.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.decompose)
                api(libs.kotlin.coroutines)
            }
        }
        val androidMain by getting {
        }
    }
}

android {
    namespace = "com.nikitakrapo.trips.utils.decompose"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
