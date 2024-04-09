plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
}

android {
    namespace = "com.nikitakrapo.design.compose"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.strings)
    api(libs.compose.ui)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.material3)
    api(libs.compose.material.icons)
    debugImplementation(libs.compose.ui.tooling)
}