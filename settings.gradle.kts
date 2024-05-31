plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "Milkyway"
include("milkyway-core")
include("milkyway-bsky")
include("sample")
include("milkyway-stream")
