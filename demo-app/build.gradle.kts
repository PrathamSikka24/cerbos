plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17 // Adjusted to Java 17, change as needed
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") // Duplicate removed
    testImplementation("org.springframework.boot:spring-boot-starter-test")
implementation("dev.cerbos:cerbos-sdk-java:0.+")
    implementation("io.grpc:grpc-netty-shaded:1.42.1") // Required for gRPC
}

tasks.withType<Test> {
    useJUnitPlatform()
}
