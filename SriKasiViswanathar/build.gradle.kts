plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.techtist.srikasiviswanathar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.techtist.srikasiviswanathar"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.squareup.okhttp3:okhttp-urlconnection:3.4.1")
    implementation ("com.squareup.okhttp3:okhttp:3.4.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.4.1")

    implementation ("com.squareup.retrofit2:converter-gson:2.1.0")
    implementation ("com.squareup.retrofit2:retrofit:2.1.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.3.0")

    implementation ("com.google.code.gson:gson:2.6.2")
    implementation ("com.github.d-max:spots-dialog:0.7@aar")

    implementation ("androidx.annotation:annotation:1.6.0")


}