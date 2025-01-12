plugins {
    kotlin("plugin.allopen")
    kotlin("plugin.spring")
    id("kotlin-jpa")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.devtools.ksp")
}

group = "sean.hwang"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // QueryDSL
    api(group = "com.querydsl", name = "querydsl-jpa", classifier = "jakarta")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jakarta")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation(kotlin("test"))
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.test {
    useJUnitPlatform()
}
