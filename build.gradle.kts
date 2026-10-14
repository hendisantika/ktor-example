val kotlin_version = project.property("kotlin_version") as String
val logback_version = project.property("logback_version") as String
val postgresql_driver_version = project.property("postgresql_driver_version") as String
val exposed_version = project.property("exposed_version") as String
val testcontainers_version = "1.21.4"

plugins {
    kotlin("jvm") version "2.4.21"
    id("io.ktor.plugin") version "3.6.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.4.21"
}

group = "id.my.hendisantika"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:${postgresql_driver_version}")
    implementation("org.jetbrains.exposed:exposed-core:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-dao:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${exposed_version}")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // TestContainers
    testImplementation("org.testcontainers:testcontainers:$testcontainers_version")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainers_version")
    testImplementation("org.testcontainers:postgresql:$testcontainers_version")
}

kotlin {
    jvmToolchain(25)
}
