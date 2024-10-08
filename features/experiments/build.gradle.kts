plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
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
                implementation(libs.kotlin.coroutines)
                implementation(libs.napier)
                implementation(libs.koin)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.experiments"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
