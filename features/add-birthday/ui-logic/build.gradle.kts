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
    }
}

android {
    namespace = "com.nikitakrapo.birthdays.add.ui_logic"
    moduleConfigurationPlugin.configureAndroidDefaults()
}
