plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    id("birthdays.module-config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    moduleConfigurationPlugin.configureMultiplatformDefaults()
    sourceSets {
        val androidMain by getting {
            dependencies {
                api(projects.features.addBirthday.uiLogic)
                implementation(projects.strings)
                implementation(projects.features.design.compose)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.add.compose-ui"
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
}
