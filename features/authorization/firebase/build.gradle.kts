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
                api(projects.features.authorization.api)
                implementation(projects.features.di)
                implementation(projects.features.utils.coroutines)
                implementation(libs.kotlin.coroutines)
                implementation(libs.napier)
                implementation(libs.kotlin.datetime)
                implementation(libs.firebase.auth.kmp)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.account.firebase"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
