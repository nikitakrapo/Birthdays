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
                api(libs.kotlin.coroutines)
            }
        }
        val androidMain by getting {
        }
    }
}

android {
    namespace = "com.nikitakrapo.trips.utils.coroutines"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
