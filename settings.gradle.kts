pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.51.0"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.6.0"
}

rootProject.name = "Daedalus"
include(":Daedalus")
