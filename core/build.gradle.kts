plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.9.23"
}

group = "com.milkcocoa.info.milkeyway"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)

    jvm { withJava() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.core)
            implementation(libs.ktor.cio)
            implementation(libs.coroutines.core)
            implementation(libs.serialization.core)
            implementation(libs.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        // for test (kotlin/jvm)
        jvmTest.dependencies {
        }
    }
}