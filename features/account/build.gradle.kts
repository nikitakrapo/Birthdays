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
                implementation(projects.features.di)
                implementation(libs.kotlin.coroutines)
                implementation(libs.napier)
                implementation(libs.kotlin.datetime)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.firebase.auth)
                implementation(libs.firebase.firestore)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.account"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
