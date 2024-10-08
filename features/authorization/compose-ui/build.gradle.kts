plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
    id("birthdays.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults(ios = false)

    sourceSets {
        val androidMain by getting {
            dependencies {
                api(projects.features.authorization.uiLogic)
                implementation(projects.features.datechooser.composeUi)
                implementation(projects.strings)
                implementation(projects.features.design.compose)
                implementation(libs.decompose.extensions.compose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.authorization.compose.ui"
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
