plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "sean.hwang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // api("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}
