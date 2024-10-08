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
                api(libs.kotlin.datetime)
                implementation(projects.features.di)
                implementation(projects.features.experiments)
                implementation(projects.features.utils.decompose)
                implementation(libs.decompose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.datechooser.ui.logic"
    moduleConfigurationPlugin.configureAndroidDefaults()
}
