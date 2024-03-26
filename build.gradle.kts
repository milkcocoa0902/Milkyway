plugins {
    kotlin("multiplatform") version "1.9.23" apply false
    kotlin("plugin.serialization") version "1.9.23" apply false
}


allprojects {
    group = "com.milkcocoa.info.milkeyway"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}
