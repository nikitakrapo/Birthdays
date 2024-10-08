plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    id("birthdays.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.repositories)
                implementation(projects.features.di)
                implementation(projects.features.utils.decompose)
                implementation(libs.decompose)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.paging.runtime)
                implementation(libs.paging.compose)
                implementation(projects.strings)
                implementation(projects.features.design.compose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.trips.feed"
    moduleConfigurationPlugin.configureAndroidDefaults()
}
