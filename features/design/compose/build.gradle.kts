plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    id("birthdays.module-config")
}

android {
    moduleConfigurationPlugin.configureAndroidDefaults()
    moduleConfigurationPlugin.configureCompose()
    namespace = "com.nikitakrapo.design.compose"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.strings)
    implementation(projects.features.experiments)
    // TODO: consider removing models dep
    api(projects.features.models)
    api(libs.compose.ui)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.material3)
    api(libs.compose.material.icons)
    api(libs.compose.shimmer)
    api(libs.coil.compose)
    api(libs.kotlin.datetime)
    debugImplementation(libs.compose.ui.tooling)
}