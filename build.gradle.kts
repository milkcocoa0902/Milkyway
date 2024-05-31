import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23" apply false
    kotlin("plugin.serialization") version "1.9.23" apply false
    `maven-publish`
    id("cl.franciscosolis.sonatype-central-upload") version "1.0.3" apply false
    jacoco
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

val ktlintCheck by tasks.registering(JavaExec::class) {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args(
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**"
    )
}
//
// tasks.named("check") {
//    dependsOn(ktlintCheck)
// }

tasks.register<JavaExec>("ktlintFormat") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style and format"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args(
        "-F",
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**"
    )
}

jacoco {
    toolVersion = "0.8.9"
//    reportsDirectory = layout.buildDirectory.dir("jacocoReport")
}

subprojects {
    withoutSampleProject {
        apply(plugin = "jacoco")

        jacoco {
            toolVersion = "0.8.9"
            reportsDirectory = layout.buildDirectory.dir("jacocoReport")
        }
        tasks.withType<Test> {
            finalizedBy("jacocoTestReport")
        }

        tasks.withType<JacocoReport> {
            dependsOn(tasks.withType<Test>())
            reports {
                xml.required.set(true)
                csv.required.set(false)
                html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
            }
        }
    }
}

tasks.register<JacocoReport>("jacocoRootReport") {
    dependsOn(subprojects.withoutSampleProject().map { it.tasks.named("test") })

    additionalSourceDirs.setFrom(
        files(
            subprojects
                .withoutSampleProject()
                .flatMap { it.the<SourceSetContainer>()["main"].allSource.srcDirs }
        )
    )
    sourceDirectories.setFrom(
        files(
            subprojects
                .withoutSampleProject()
                .flatMap { it.the<SourceSetContainer>()["main"].allSource.srcDirs }
        )
    )

    classDirectories.setFrom(
        files(
            subprojects
                .withoutSampleProject()
                .flatMap { it.the<SourceSetContainer>()["main"].output.classesDirs }
        )
    )
    executionData.setFrom(
        files(
            subprojects
                .withoutSampleProject()
                .flatMap {
                    it.tasks.withType<JacocoReport>().flatMap { it.executionData.files }
                }
        )
    )

    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoRootReportHtml"))
    }
}

fun MutableSet<Project>.withoutSampleProject() = filter { it.name == "sample" }

fun Project.withoutSampleProject(action: Action<in Project>) =
    run {
        if (name != "sample") {
            action(this)
        }
    }