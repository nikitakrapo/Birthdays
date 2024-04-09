plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
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
