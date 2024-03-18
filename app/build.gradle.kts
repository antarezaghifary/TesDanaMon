plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.antareza.tesdanamon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.antareza.tesdanamon"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
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
        buildConfig = true
        viewBinding = true
    }
    buildFeatures {
        buildConfig = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {

    implementation(Deps.kotlin_sblib)
    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.junit)
    implementation(Deps.testRunner)
    implementation(Deps.espresso)
    implementation(Deps.navigationIu)
    implementation(Deps.navigationFragment)
    implementation(Deps.retrofit2)
    implementation(Deps.converterGson)
    implementation(Deps.okhttp)
    implementation(Deps.okhttpInterceptor)
    implementation(Deps.chucker)
    implementation(Deps.rxAndroid)
    implementation(Deps.rxJava)
    implementation(Deps.rxBinding)
    implementation(Deps.glide)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    kapt(Deps.glideCompiler)
    implementation(Deps.pretty)
    implementation(Deps.dagger)
    kapt(Deps.hiltCompiler)
    kapt(Deps.hiltAndroidCompiler)
    implementation(Deps.rxJava2)
    implementation(Deps.room)
    implementation(Deps.roomRuntime)
    implementation(Deps.roomRxJava)
    implementation(Deps.roomPaging)
    implementation(Deps.pagingRxJava)
    implementation(Deps.pagingRuntime)
    kapt(Deps.roomCompiler)
    testImplementation(Deps.androidJunit)
    androidTestImplementation(Deps.espresso)
    androidTestImplementation(Deps.junit)
    implementation(Deps.koin)
    implementation(Deps.startup)
    implementation(Deps.extAdd)
    implementation(Deps.rxKotlin)
    implementation(Deps.roomRx3)
}