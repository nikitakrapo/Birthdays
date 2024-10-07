package com.nikitakrapo.birthdays.config

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.nikitakrapo.birthdays.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

abstract class ModuleConfigurationExtension(private val project: Project) {

    companion object {
        private const val COMPILE_SDK = 34
        private const val MIN_SDK = 26
    }

    fun configureAndroidDefaults() {
        project.configure<LibraryExtension> {
            compileSdk = COMPILE_SDK
            defaultConfig {
                minSdk = MIN_SDK
            }
        }
    }

    fun configureAndroidApp(appId: String, appVersionCode: Int, appVersionName: String) {
        project.plugins.apply(project.libs.plugins.composeCompiler.get().pluginId)
        project.configure<BaseAppModuleExtension> {
            compileSdk = COMPILE_SDK
            defaultConfig {
                minSdk = MIN_SDK
            }
            defaultConfig {
                applicationId = appId
                targetSdk = 34
                versionCode = appVersionCode
                versionName = appVersionName
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    // FIXME: remove before publishing
                    signingConfig = signingConfigs.getByName("debug")
                }
            }
            buildFeatures {
                compose = true
            }
        }
    }

    fun configureCompose() {
        project.plugins.apply(project.libs.plugins.composeCompiler.get().pluginId)
        project.configure<LibraryExtension> {
            buildFeatures {
                compose = true
            }
        }
    }

    fun configureMultiplatformDefaults() {
        project.configure<KotlinMultiplatformExtension> {
            applyDefaultHierarchyTemplate()

            iosX64()
            iosArm64()
            iosSimulatorArm64()

            sourceSets.getByName("commonMain") {
                dependencies {
                    implementation(project.libs.napier)
                }
            }

            androidTarget {
                compilations.all {
                    kotlinOptions {
                        jvmTarget = "1.8"
                    }
                }
            }
        }
    }
}