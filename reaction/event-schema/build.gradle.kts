import com.github.imflog.schema.registry.Subject
import com.google.protobuf.gradle.id

buildscript {
    repositories {
        gradlePluginPortal()
        maven("https://packages.confluent.io/maven/")
        maven("https://jitpack.io")
    }
}

plugins {
    id("com.github.imflog.kafka-schema-registry-gradle-plugin") version "2.1.1"
    id("com.google.protobuf")
    id("maven-publish")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

schemaRegistry {
    url.set("http://localhost:8086")

    val likeEventSubject = Subject("likes.event-value", "event-schema/schemas/like_event.proto", "PROTOBUF")

    register {
        subject(likeEventSubject)
    }

    compatibility {
        subject(likeEventSubject)
    }

    config {
        subject(likeEventSubject.inputSubject, "FULL_TRANSITIVE")
    }
}

val protoVersion = "3.25.5"

dependencies {
    api("com.google.protobuf:protobuf-kotlin:$protoVersion")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protoVersion" // protoc 버전 설정
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("kotlin")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("schemas")
        }

        kotlin {
            srcDir("build/generated/source/proto/main/kotlin")
        }
    }
}

tasks.register<Jar>("protoSourcesJar") {
    dependsOn("generateProto")
    from("build/generated/source/proto/main")
    archiveClassifier.set("proto-sources")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            // Include generated sources
            artifact(tasks.named("protoSourcesJar")) {
                classifier = "proto-sources"
            }

            groupId = "sean.hwang.mockstagram"
            artifactId = "reaction-event-schema"
            version = "0.0.1"
        }
    }
}
