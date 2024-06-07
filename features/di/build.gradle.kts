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
                api(libs.koin)
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.di"
    compileSdk = 34
    defaultConfig.minSdk = 24
}
