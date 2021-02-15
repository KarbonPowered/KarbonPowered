plugins {
    kotlin("multiplatform") version "1.4.30"
    application
}

allprojects {
    apply(plugin = "kotlin-multiplatform")

    group = "com.karbonpowered"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    kotlin {
        targets {
            jvm()
        }

        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation("org.jetbrains.kotlinx:atomicfu:0.15.1")
                    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
                    implementation("io.ktor:ktor-io:1.5.1")
                }
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            useIR = true
            jvmTarget = "11"
        }
    }
}