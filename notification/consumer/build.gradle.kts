plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib")
}

dependencies {
    implementation(project(":domain"))

    implementation("sean.hwang.mockstagram:comment-api-stubs:0.0.1")
    implementation("sean.hwang.mockstagram:reaction-api-stubs:0.0.1")
    implementation("sean.hwang.mockstagram:user-api-stubs:0.0.1")

    implementation("sean.hwang.mockstagram:comment-event-schema:0.0.1")
    implementation("sean.hwang.mockstagram:reaction-event-schema:0.0.1")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")
    implementation("io.confluent:kafka-protobuf-serializer:7.8.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

jib {
    from {
        image = "openjdk:17-jdk-slim" // 베이스 이미지
    }
    to {
        image = "mockstagram-notification-consumer"
    }
    container {
        jvmFlags = listOf("-Xms512m", "-Xmx1024m")
        ports = listOf("51056")
    }
}

tasks.named("build") {
    dependsOn("jibDockerBuild")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("prepareKotlinBuildScriptModel") {}
