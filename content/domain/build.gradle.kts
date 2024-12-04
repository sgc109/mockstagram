plugins {
    kotlin("plugin.allopen")
    kotlin("plugin.spring")
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
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    api("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

    implementation("com.querydsl:querydsl-mongodb") {
        exclude(group = "org.mongodb", module = "mongo-java-driver")
    }
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "general")

    api("org.mongodb:bson")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kapt {
    arguments {
        arg("kapt.kotlin.generated", file("build/generated/source/kaptKotlin"))
    }
    javacOptions {
        option("-J--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
    }

    annotationProcessor("org.springframework.data.mongodb.repository.support.MongoAnnotationProcessor")
    annotationProcessor("com.querydsl.apt.QuerydslAnnotationProcessor")
}
