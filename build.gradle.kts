plugins {
    kotlin("jvm") version "1.9.23" apply false
    kotlin("plugin.serialization") version "1.9.23" apply false
}


allprojects {
    group = "com.milkcocoa.info.milkyway"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}
