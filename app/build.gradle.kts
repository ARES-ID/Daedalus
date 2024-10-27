import org.gradle.internal.jvm.inspection.JvmVendor
import org.gradle.jvm.toolchain.internal.DefaultJvmVendorSpec
import java.io.ByteArrayOutputStream

plugins {
    alias(libs.plugins.comAndroidApplication)
    alias(libs.plugins.orgJetbrainsKotlinAndroid)
    alias(libs.plugins.comGoogleDevtoolsKsp)
    alias(libs.plugins.ioGitlabArturboschDetekt)
    alias(libs.plugins.orgJmailenKotlinter)
    alias(libs.plugins.orgJetbrainsKotlinPluginCompose)
    alias(libs.plugins.orgJetbrainsKotlinPluginParcelize)
}

android {
    namespace = libs.versions.namespace.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.namespace.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = generateVersionCode()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore/release.keystore")
            storePassword = System.getenv("DAEDALUS_STORE_PASSWORD")
            keyAlias = System.getenv("DAEDALUS_SIGNING_KEY")
            keyPassword = System.getenv("DAEDALUS_SIGNING_KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            ndk.debugSymbolLevel = "FULL"
            signingConfig = signingConfigs["release"]
        }

        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isCrunchPngs = false
            signingConfig = signingConfigs["debug"]
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
        vendor.set(DefaultJvmVendorSpec.of(JvmVendor.fromString(libs.versions.javaVendor.get()).knownVendor))
    }

    compilerOptions {
        allWarningsAsErrors.set(true)
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(libs.comJakewhartonTimer.timber)
    implementation(libs.androidxActivity.activityCompose)
    implementation(libs.androidxComposeUi.ui)
    implementation(libs.androidxCore.coreKtx)
    implementation(libs.androidxCore.coreSplashscreen)
    implementation(libs.androidxLifecycle.lifecycleRuntimeKtx)
    implementation(libs.androidxNavigation.navigationCompose)
    implementation(libs.androidxComposeMaterial3.material3)
    implementation(libs.ioInsertKoin.koinAndroidxCompose)
    implementation(libs.comPatrykandpatrickVico.composeM3)
    implementation(libs.androidxComposeUi.uiTextGoogleFonts)
    implementation(libs.androidxRoom.roomKtx)
    implementation(libs.androidxRoom.roomRuntime)
    implementation(libs.androidxConstraintlayout.constraintlayoutCompose)
    ksp(libs.androidxRoom.roomCompiler)
    testImplementation(libs.ioInsertKoin.koinTestJunit4)
}

fun generateVersionCode(): Int {
    val standardOutput = ByteArrayOutputStream()
    project.exec {
        commandLine("git", "rev-list", "--count", "HEAD")
        this.standardOutput = standardOutput
    }
    val commitCount = standardOutput.toString().trim().toInt()
    val offset = libs.versions.versionCodeOffset.get().toInt()
    val versionCode = commitCount + offset
    logger.debug("Generating version code = $versionCode")
    return versionCode
}

tasks.create("version") {
    doLast {
        println(libs.versions.versionName.get())
    }
}
