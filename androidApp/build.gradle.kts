plugins {
    id(libs.plugins.androidApplication.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.nikitakrapo.trips"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.nikitakrapo.trips"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.features.di)
    implementation(projects.shared)
    implementation(projects.strings)
    implementation(projects.features.network)
    implementation(projects.features.design.compose)
    implementation(libs.decompose)
    implementation(libs.decompose.extensions.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons)
    implementation(libs.androidx.activity.compose)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.koin)
    implementation(libs.napier)
    debugImplementation(libs.compose.ui.tooling)
}