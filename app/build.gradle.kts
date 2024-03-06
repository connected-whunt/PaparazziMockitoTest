plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("app.cash.paparazzi") version "1.3.3"
}

android {
    namespace = "com.example.papparazitest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.papparazitest"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"//"1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}




dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)

testImplementation(libs.mockitoKotlin)
//    testImplementation(libs.mockitoCore)
    testImplementation(libs.mockitoInline)

//    api(libs.mockitoKotlin) {
//        exclude(group = "org.jetbrains.kotlin")
//    }
//
//    api(libs.mockitoInline)

//    api(libs.kotlinCoroutineTest) {
//        // conflicts with mockito due to direct inclusion of byte buddy
//        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
//    }
}