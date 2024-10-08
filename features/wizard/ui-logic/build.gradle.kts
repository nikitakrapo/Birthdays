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
                api(libs.decompose)
                implementation(projects.features.di)
                implementation(projects.features.datechooser.uiLogic)
                implementation(projects.features.experiments)
                implementation(projects.features.utils.decompose)
                implementation(libs.kotlin.datetime)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.wizard.ui.logic"
    moduleConfigurationPlugin.configureAndroidDefaults()
}
