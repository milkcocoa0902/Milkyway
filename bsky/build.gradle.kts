plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.23"
}

group = "com.milkcocoa.info.milkyway"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
}

dependencies{
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.coroutines.core)
    implementation(libs.serialization.core)
    implementation(libs.serialization.json)
    implementation(libs.serialization.properties)
    testImplementation(libs.kotlin.test)


    api(project(":core"))
}