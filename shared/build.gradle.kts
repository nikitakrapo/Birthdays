plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    applyDefaultHierarchyTemplate()

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
                api(projects.features.di)
                api(projects.features.authorization)
                api(projects.features.profile)
                api(projects.features.feed)
                implementation(projects.features.account)
                implementation(projects.features.network)
                implementation(projects.features.repositories)
                implementation(libs.decompose)
                implementation(projects.features.utils.decompose)
                implementation(projects.features.utils.coroutines)
                implementation(libs.koin)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.strings)
                implementation(projects.features.design.compose)
                implementation(libs.decompose.extensions.compose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.trips"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}
