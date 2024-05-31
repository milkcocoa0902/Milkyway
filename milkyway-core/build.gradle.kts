import cl.franciscosolis.sonatypecentralupload.SonatypeCentralUploadTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.23"
    `maven-publish`
    id("cl.franciscosolis.sonatype-central-upload") version "1.0.3"
}

group = "io.github.milkcocoa0902"
version = "0.0.8"
java.sourceCompatibility = JavaVersion.VERSION_11
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

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

val CORE_LIBRARY_DESCRIPTION: String by project
val PROJECT_URL: String by project
val LICENSE_TYPE: String by project
val LICENSE_URL: String by project
val LICENSE_DISTRIBUTION: String by project
val DEVELOPER_ID: String by project
val DEVELOPER_NAME: String by project
val DEVELOPER_EMAIL: String by project
val REPOSITORY_URL: String by project

java {
    withSourcesJar()
    withJavadocJar()
}
publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components.named("java").get())

            pom {
                name.set(project.name)
                // 説明
                description.set(CORE_LIBRARY_DESCRIPTION)
                // URL
                url.set(PROJECT_URL)
                // ライセンス
                licenses {
                    license {
                        name.set(LICENSE_TYPE)
                        url.set(LICENSE_URL)
                        distribution.set(LICENSE_DISTRIBUTION)
                    }
                }
                // 開発者
                developers {
                    developer {
                        id.set(DEVELOPER_ID)
                        name.set(DEVELOPER_NAME)
                        email.set(DEVELOPER_EMAIL)
                    }
                }
                // バージョン管理システム
                scm {
                    url.set(REPOSITORY_URL)
                }
            }
        }
    }
}

tasks.named<SonatypeCentralUploadTask>("sonatypeCentralUpload") {
    // 公開するファイルを生成するタスクに依存する。
    dependsOn(
        "jar",
        "sourcesJar",
        "javadocJar",
        "generatePomFileForMavenPublication"
    )

    // Central Portalで生成したトークンを指定する。
    username = System.getenv("SONATYPE_CENTRAL_USERNAME")
    password = System.getenv("SONATYPE_CENTRAL_PASSWORD")

    // タスク名から成果物を取得する。
    archives =
        files(
            tasks.named("jar"),
            tasks.named("sourcesJar"),
            tasks.named("javadocJar")
        )
    // POMファイルをタスクの成果物から取得する。
    pom =
        file(
            tasks.named("generatePomFileForMavenPublication").get().outputs.files.single()
        )

    // PGPの秘密鍵を指定する。
    signingKey = System.getenv("PGP_SIGNING_KEY")
    // PGPの秘密鍵のパスフレーズを指定する。
    signingKeyPassphrase = System.getenv("PGP_SIGNING_KEY_PASSPHRASE")
}