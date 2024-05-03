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

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.coroutines.core)
    implementation(libs.serialization.core)
    implementation(libs.serialization.json)
    implementation(libs.serialization.properties)
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}