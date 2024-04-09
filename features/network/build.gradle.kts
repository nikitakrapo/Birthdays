plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

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
                implementation(libs.ktor.auth)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.contentNegotiation)
                implementation(libs.ktor.contentNegotiation.json)
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
    namespace = "com.nikitakrapo.trips.network"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
