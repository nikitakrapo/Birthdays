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
                api(projects.features.authorization.api)
                api(projects.features.datechooser.uiLogic)
                api(projects.features.di)
                api(projects.features.utils.decompose)
                implementation(libs.decompose)
                implementation(libs.napier)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.authorization.ui.logic"
    moduleConfigurationPlugin.configureAndroidDefaults()
}
