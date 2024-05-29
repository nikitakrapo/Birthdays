plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
    id("trips.module-config")
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
                api(projects.features.cloudMessaging)
                api(projects.features.utils.platform)
                implementation(projects.features.account)
                implementation(projects.features.network)
                implementation(projects.features.repositories)
                implementation(projects.features.wizard)
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
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
