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

dependencies {
    implementation(project(":milkyway-core"))
    implementation(project(":milkyway-bsky"))
}