plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
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
                api(libs.ktor.core)
                api(libs.ktor.auth)
                api(libs.ktor.logging)
                api(libs.ktor.contentNegotiation)
                api(libs.ktor.contentNegotiation.json)
                implementation(libs.napier)
                implementation(projects.features.di)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.ktor.okhttp)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.network"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
