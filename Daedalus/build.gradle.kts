import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.rjspies.daedalus"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.rjspies.daedalus"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = generateVersionCode()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resourceConfigurations.addAll(arrayOf("en", "de"))
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            ndk.debugSymbolLevel = "FULL"
        }

        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
            isCrunchPngs = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.timber)
    implementation(libs.vico.compose)
    implementation(libs.accompanist.systemUiController)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.bundles.androidTest)
    debugImplementation(libs.bundles.debug)
    ksp(libs.androidx.room.compiler)
}

detekt {
    baseline = file("${rootDir.path}/config/detekt/baseline.xml")
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

fun generateVersionCode(): Int {
    val version = libs.versions.versionName.get().replace(".", "")
    val formatter = DateTimeFormatter.ofPattern("yyMMdd").withZone(ZoneId.systemDefault())
    val date = formatter.format(Instant.now())
    return "$date$version".toInt()
}
