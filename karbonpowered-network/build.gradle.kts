kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":karbonpowered-common"))
                api(project(":karbonpowered-io"))
                api("io.ktor:ktor-network:1.5.1")
            }
        }
    }
}