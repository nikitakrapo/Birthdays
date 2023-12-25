plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
                implementation(libs.ktor.logging)
                implementation(libs.napier)
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