import java.io.ByteArrayOutputStream

plugins {
    alias(libs.plugins.comAndroidApplication)
    alias(libs.plugins.orgJetbrainsKotlinAndroid)
    alias(libs.plugins.comGoogleDevtoolsKsp)
    alias(libs.plugins.orgJmailenKotlinter)
    alias(libs.plugins.ioGitlabArturboschDetekt)
    id("kotlin-parcelize")
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

        create("beta") {
            versionNameSuffix = "-beta"
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs["release"]
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
        vendor.set(JvmVendorSpec.AZUL)
    }

    compilerOptions {
        allWarningsAsErrors.set(true)
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":data"))
    implementation(libs.androidxActivity.activityCompose)
    implementation(libs.androidxComposeUi.ui)
    implementation(libs.androidxCore.coreKtx)
    implementation(libs.androidxCore.coreSplashscreen)
    implementation(libs.androidxLifecycle.lifecycleRuntimeKtx)
    implementation(libs.androidxNavigation.navigationCompose)
    implementation(libs.androidxComposeMaterial3.material3)
    implementation(libs.androidxConstraintlayout.constraintlayoutCompose)
    implementation(libs.androidxComposeMaterial.materialIconsExtended)
    implementation(libs.comJakewhartonTimer.timber)
    implementation(libs.comPatrykandpatrickVico.composeM3)
    implementation(libs.ioInsertKoin.koinAndroidxCompose)
    implementation(libs.ioInsertKoin.koinAnnotations)
    ksp(libs.ioInsertKoin.koinKspCompiler)
    testImplementation(libs.orgJunitJupiter.junitJupiterApi)
    testImplementation(libs.ioInsertKoin.koinTestJunit4)
    testRuntimeOnly(libs.orgJunitJupiter.junitJupiterEngine)
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
