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
                api(libs.paging)
                api(libs.kotlin.datetime)
                api(projects.features.modelsDto)
                api(projects.features.authorization.api)
                api(projects.features.network)
                api(projects.features.di)
                api(projects.features.utils.coroutines)
                implementation(libs.kotlin.serialization)
                implementation(libs.napier)
            }
        }
    }
}

android {
    moduleConfigurationPlugin.configureAndroidDefaults()
    namespace = "com.nikitakrapo.birthdays.repositories"
}
