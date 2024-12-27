import com.google.protobuf.gradle.id

plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.jpa")
    id("com.google.protobuf")
    id("com.google.cloud.tools.jib")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

val grpcKotlinVersion = "1.4.1"
val grpcVersion = "1.68.1"
val protoVersion = "3.25.5"
val armeriaVersion = "1.31.0"
val nettyVersion = "4.1.115.Final"

dependencies {
    implementation(project(":domain"))

    // armeria
    // https://github.com/line/armeria-examples/blob/main/grpc/build.gradle
    implementation(platform("io.netty:netty-bom:$nettyVersion"))
    implementation(platform("com.linecorp.armeria:armeria-bom:$armeriaVersion"))

    implementation("com.linecorp.armeria:armeria-kotlin")
    implementation("com.linecorp.armeria:armeria-spring-boot3-webflux-starter")
    implementation("com.linecorp.armeria:armeria-spring-boot3-actuator-starter")
    implementation("com.linecorp.armeria:armeria-grpc")

    // grpc, protobuf
    api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion") // kotlin stub 제공
    api("com.google.protobuf:protobuf-kotlin:$protoVersion") // kotlin 코드 생성 도구

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

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
            srcDir("proto") // 루트 레벨의 proto 디렉터리를 추가
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
        image = "mockstagram-comment-api"
    }
    container {
        jvmFlags = listOf("-Xms512m", "-Xmx1024m")
        ports = listOf("50051")
    }
}

tasks.named("build") {
    dependsOn("jibDockerBuild")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
