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
                api(projects.features.account)
                api(projects.features.di)
                implementation(libs.decompose)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.strings)
                implementation(projects.features.design.compose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.trips.authorization"
    compileSdk = 34
    defaultConfig.minSdk = 24
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}
