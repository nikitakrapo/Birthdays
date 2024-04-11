plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.agp)
    implementation(libs.kotlin.plugin)
    implementation(libs.kotlin.pluginApi)
}
