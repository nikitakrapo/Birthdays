plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
    id("birthdays.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.account)
                api(projects.features.repositories)
                api(projects.features.datechooser)
                api(projects.features.di)
                api(projects.features.utils.decompose)
                api(projects.features.utils.platform)
                implementation(libs.decompose)
                implementation(libs.napier)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.strings)
                implementation(projects.features.design.compose)
                implementation(libs.decompose.extensions.compose)
                implementation(libs.compose.shimmer)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.profile"
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
