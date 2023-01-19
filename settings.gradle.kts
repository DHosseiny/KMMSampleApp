pluginManagement {
    val kspVersion = "1.7.21-1.0.8"

    plugins {
        id("com.google.devtools.ksp") version kspVersion apply false
    }
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KMMApp"

include(":androidApp")
include(":shared")

