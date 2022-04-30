import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.lderic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jline:jline:3.21.0")
    testImplementation(kotlin("test"))
}

tasks.jar {
    manifest {
        attributes(Pair("Main-Class", "com.lderic.mcbridge.MCBridge"))
    }
}

tasks.shadowJar {
    manifest {
        attributes(Pair("Main-Class", "com.lderic.mcbridge.MCBridge"))
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}