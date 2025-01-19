import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.moony.offlinemusic"
    compileSdk = 35


    // 로컬 프로퍼티 사용 부분
    signingConfigs {
        create("release") {
            val localProperties = Properties()
            val localPropertiesFile = rootProject.file("local.properties")
            localProperties.load(localPropertiesFile.inputStream())
            storeFile = file(localProperties.getProperty("key.store.file"))
            storePassword = localProperties.getProperty("key.store.password")
            keyAlias = localProperties.getProperty("key.alias.name")
            keyPassword = localProperties.getProperty("key.alias.password")
        }
    }
    defaultConfig {
        applicationId = "com.moony.offlinemusic"
        minSdk = 24
        targetSdk = 35
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
            applicationIdSuffix=".dev"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)


    //compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    //media3
    implementation(libs.androidx.media3.session)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // paging3
    implementation(libs.androidx.paging.compose)

    //system ui
    implementation(libs.accompanist.systemuicontroller)

    //glide
    implementation(libs.glide.compose)


    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:music_player"))
    implementation(project(":feature:media_service"))
    implementation(project(":feature:playlist"))


}
