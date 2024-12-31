import com.google.protobuf.gradle.id

plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.jpa")
    id("com.google.protobuf")
    id("com.google.cloud.tools.jib")
    id("maven-publish")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

val grpcKotlinVersion = "1.4.1"
val grpcVersion = "1.61.1"
val protoVersion = "3.25.1"
val armeriaVersion = "1.31.0"
val nettyVersion = "4.1.115.Final"

dependencies {
    implementation(project(":domain"))
    implementation(project(":event-schema"))

    // armeria
    // https://github.com/line/armeria-examples/blob/main/grpc/build.gradle
    implementation(platform("io.netty:netty-bom:$nettyVersion"))
    implementation(platform("com.linecorp.armeria:armeria-bom:$armeriaVersion"))

    implementation("com.linecorp.armeria:armeria-kotlin")
    implementation("com.linecorp.armeria:armeria-spring-boot3-webflux-starter")
    implementation("com.linecorp.armeria:armeria-spring-boot3-actuator-starter")
    implementation("com.linecorp.armeria:armeria-grpc")
    implementation("org.springframework.kafka:spring-kafka")

    // grpc, protobuf
    api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    api("com.google.protobuf:protobuf-kotlin:$protoVersion")
    api("com.google.protobuf:protobuf-java:$protoVersion")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.confluent:kafka-protobuf-serializer:7.8.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protoVersion" // protoc 버전 설정
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
                id("grpckt")
            }
            task.builtins {
                id("kotlin")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("proto")
        }

        kotlin {
            srcDir("build/generated/source/proto/main/java")
            srcDir("build/generated/source/proto/main/grpc")
            srcDir("build/generated/source/proto/main/grpckt")
        }
    }
}

jib {
    from {
        image = "openjdk:17-jdk-slim" // 베이스 이미지
    }
    to {
        image = "mockstagram-reaction-api"
    }
    container {
        jvmFlags = listOf("-Xms512m", "-Xmx1024m")
        ports = listOf("50051")
    }
}

tasks.named("build") {
    dependsOn("jibDockerBuild")
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
            artifactId = "reaction-api-stubs"
            version = "0.0.1"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
