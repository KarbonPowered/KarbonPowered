kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":karbonpowered-protocol"))
                api(project(":karbonpowered-math"))
            }
        }
    }
}

application {
    mainClass.set("com.karbonpowered.engine.MainKt")
}