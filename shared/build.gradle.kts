import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id(libs.plugins.kotlinMultiplatform.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.kotlinSerialization)
    id("birthdays.module-config")
}

kotlin {
    applyDefaultHierarchyTemplate()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "shared"
            isStatic = true

            export(projects.features.authorization.uiLogic)
            export(libs.decompose)
            export(libs.essenty.lifecycle)
            export(libs.essenty.statekeeper)
            export(libs.firebase.auth.kmp)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.di)
                api(projects.features.authorization.uiLogic)
                api(projects.features.profile.uiLogic)
                api(projects.features.feed.uiLogic)
                api(projects.features.cloudMessaging)
                api(projects.features.utils.platform)
                api(libs.decompose)
                api(libs.essenty.lifecycle)
                api(libs.essenty.statekeeper)
                implementation(projects.features.authorization.firebase)
                implementation(projects.features.network)
                implementation(projects.features.repositories)
                implementation(projects.features.wizard.uiLogic)
                implementation(projects.features.utils.decompose)
                implementation(projects.features.utils.coroutines)
                implementation(libs.koin)
            }
        }
    }
}

android {
    namespace = "com.nikitakrapo.birthdays"
    moduleConfigurationPlugin.configureAndroidDefaults()
}
