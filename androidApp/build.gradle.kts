plugins {
    id(libs.plugins.androidApplication.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.kotlinSerialization)
    id("trips.module-config")
}

android {
    namespace = "com.nikitakrapo.trips"
    moduleConfigurationPlugin.configureAndroidApp(
        appId = "com.nikitakrapo.trips",
        appVersionCode = 1,
        appVersionName = "1.0",
    )
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