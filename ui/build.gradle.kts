plugins {
    alias(libs.plugins.comAndroidLibrary)
    alias(libs.plugins.ioGitlabArturboschDetekt)
    alias(libs.plugins.orgJetbrainsKotlinAndroid)
    alias(libs.plugins.orgJmailenKotlinter)
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

        create("beta") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
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
        explicitApi()
    }
}

dependencies {
    implementation(libs.comJakewhartonTimer.timber)
    implementation(libs.androidxComposeMaterial3.material3)
    implementation(libs.androidxNavigation.navigationCompose)
    testImplementation(libs.orgJunitJupiter.junitJupiterApi)
    testRuntimeOnly(libs.orgJunitJupiter.junitJupiterEngine)
}
