import org.gradle.internal.jvm.inspection.JvmVendor
import org.gradle.jvm.toolchain.internal.DefaultJvmVendorSpec

plugins {
    alias(libs.plugins.comAndroidLibrary)
    alias(libs.plugins.orgJetbrainsKotlinAndroid)
    alias(libs.plugins.comGoogleDevtoolsKsp)
    alias(libs.plugins.ioGitlabArturboschDetekt)
    alias(libs.plugins.orgJmailenKotlinter)
    alias(libs.plugins.ioGithubAdityahaskarDependencygraph)
    alias(libs.plugins.orgJetbrainsKotlinPluginCompose)
    id("kotlin-parcelize")
}

android {
    namespace = libs.versions.namespace.get() + ".ui"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            ndk.debugSymbolLevel = "FULL"
        }
    }

    buildFeatures {
        compose = true
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
        explicitApi()
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(libs.comJakewhartonTimer.timber)
    implementation(libs.androidxComposeMaterial3.material3)
    implementation(libs.androidxComposeMaterial.materialIconsExtendedAndroid)
    implementation(libs.androidxNavigation.navigationCompose)
    implementation(libs.ioInsertKoin.koinAndroidxCompose)
    implementation(libs.ioInsertKoin.koinAnnotations)
    implementation(libs.comPatrykandpatrickVico.composeM3)
    implementation(libs.androidxConstraintlayout.constraintlayoutCompose)
    implementation(libs.androidxComposeUi.uiTextGoogleFonts)
    ksp(libs.ioInsertKoin.koinKspCompiler)
    testImplementation(libs.orgJunitJupiter.junitJupiterApi)
    testRuntimeOnly(libs.orgJunitJupiter.junitJupiterEngine)
}
