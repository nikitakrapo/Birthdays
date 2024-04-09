plugins {
    // TODO: https://github.com/gradle/gradle/issues/17968
    id(libs.plugins.androidApplication.get().pluginId).apply(false)
    id(libs.plugins.androidLibrary.get().pluginId).apply(false)
    id(libs.plugins.kotlinAndroid.get().pluginId).apply(false)
    id(libs.plugins.kotlinMultiplatform.get().pluginId).apply(false)
    alias(libs.plugins.googleServices).apply(false)
    alias(libs.plugins.firebaseCrashlytics).apply(false)
}
