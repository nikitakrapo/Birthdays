package com.nikitakrapo.trips.config

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.nikitakrapo.trips.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import kotlin.math.min

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
        project.configure<BaseAppModuleExtension> {
            compileSdk = COMPILE_SDK
            defaultConfig {
                minSdk = MIN_SDK
            }
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = project.libs.versions.compose.compiler.get()
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
        }
    }

    fun configureCompose() {
        project.configure<LibraryExtension> {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = project.libs.versions.compose.compiler.get()
            }
        }
    }

    fun configureMultiplatformDefaults() {
        project.configure<KotlinMultiplatformExtension> {
            applyDefaultHierarchyTemplate()

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