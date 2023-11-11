plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "dependencies.gradle.kts")

android {
    namespace = libs.versions.namespace.get() + ".ui"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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
    implementation(libs.composeMaterial3)
    implementation(libs.accompanistSystemUiController)
}
