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
                implementation(libs.kotlin.coroutines)
                implementation(libs.napier)
                implementation(libs.koin)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.firebase.auth)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.trips.account"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
