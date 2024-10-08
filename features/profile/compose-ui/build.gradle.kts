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
        val androidMain by getting {
            dependencies {
                api(projects.features.profile.uiLogic)
                implementation(projects.features.datechooser.composeUi)
                implementation(projects.strings)
                implementation(projects.features.design.compose)
                implementation(libs.decompose.extensions.compose)
                implementation(libs.compose.shimmer)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.profile.compose.ui"
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
