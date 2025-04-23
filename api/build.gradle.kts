plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.ktor.plugin")
    application
}

dependencies {
    implementation(project(":shared"))
    
    // Ktor server
    implementation("io.ktor:ktor-server-config-yaml:2.3.3")
    implementation("io.ktor:ktor-server-core:2.3.3")
    implementation("io.ktor:ktor-server-netty:2.3.3")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")
    
    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.11")
    implementation("io.ktor:ktor-client-resources:3.1.2")

    // Testing
    testImplementation("io.ktor:ktor-server-test-host:2.3.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.0")
}

application {
    mainClass.set("com.todolist.api.ApplicationKt")
}