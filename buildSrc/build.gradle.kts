plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.agp)
    implementation(libs.kotlin.plugin)
    implementation(libs.kotlin.pluginApi)
}
