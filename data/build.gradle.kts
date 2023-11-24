plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.detekt)
}

android {
    namespace = libs.versions.namespace.get() + ".data"
    compileSdk = libs.versions.compileSdk.int()

    defaultConfig {
        minSdk = libs.versions.minSdk.int()
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
}

@kotlin.jvm.Throws(NumberFormatException::class)
fun Provider<String>.int() = get().toInt()

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

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.roomCompiler)
}
