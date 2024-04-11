package com.nikitakrapo.trips.config

import com.android.build.gradle.LibraryExtension
import com.nikitakrapo.trips.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

abstract class ModuleConfigurationExtension(private val project: Project) {

    fun configureAndroidDefaults() {
        project.configure<LibraryExtension> {
            compileSdk = 34
            defaultConfig {
                minSdk = 24
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

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    fun configureMultiplatformDefaults() {
        project.configure<KotlinMultiplatformExtension> {
            targetHierarchy.default()

            this.sourceSets.getByName("commonMain") {
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