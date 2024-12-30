plugins {
    val kotlinVersion = "1.9.21"
    val kspVersion = "1.9.21-1.0.16"
    id("java")
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion apply false
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
    id("org.springframework.boot") version "3.3.5" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    id("com.google.protobuf") version "0.9.4" apply false
    id("com.google.cloud.tools.jib") version "3.4.4" apply false
    id("com.google.devtools.ksp") version kspVersion
}

group = "sean.hwang"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven("https://packages.confluent.io/maven/")
        mavenLocal()
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

subprojects {
    apply {
        plugin("idea")
        plugin("kotlin")
        plugin("kotlin-kapt")
    }

    dependencies {
        // SpringBoot
        val springBootBom = platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)

        implementation(springBootBom)
        kapt(springBootBom)

        // Kotlin
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

        runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64")
        runtimeOnly("com.mysql:mysql-connector-j:8.4.0")
    }
}
