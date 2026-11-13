plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "co.edu.sena.registrologin"
    compileSdk = 36

    defaultConfig {
        applicationId = "co.edu.sena.registrologin"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        // 10.0.2.2 es el localhost de la maquina anfitriona visto desde el emulador.
        buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:3000/api\"")
    }

    buildFeatures { buildConfig = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}
