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
        }
    }
}

@kotlin.jvm.Throws(NumberFormatException::class)
fun Provider<String>.int() = get().toInt()

kotlin {
    explicitApi()
    jvmToolchain(libs.versions.java.get().toInt())
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.roomCompiler)
}
