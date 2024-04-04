import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest.ktlint:ktlint-cli:1.2.1") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
    // ktlint(project(":custom-ktlint-ruleset")) // in case of custom ruleset
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args = listOf("**/*.kt", "**.kts", "!**/build/**")
    jvmArgs = listOf("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args = listOf("-F", "**/*.kt", "**.kts", "!**/build/**")
    jvmArgs = listOf("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}