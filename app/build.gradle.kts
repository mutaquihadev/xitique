import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("de.mannodermaus.android-junit5") version "1.10.0.0"
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    namespace = "com.chamwari.tech.xitique"
    compileSdk = 34
    android.buildFeatures.buildConfig = true
    defaultConfig {
        applicationId = "com.chamwari.tech.xitique"
        minSdk = 27
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


    defaultConfig {
        val secretsPropertiesFile = rootProject.file("config.properties")
        if (secretsPropertiesFile.exists()) {
            val secrets = Properties().apply {
                load(secretsPropertiesFile.inputStream())
            }
            secrets.forEach { (key, value) ->
                buildConfigField("String", key as String, "\"$value\"")
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("com.willowtreeapps.assertk:assertk:0.26.1")

    // Ktor client core
    implementation("io.ktor:ktor-client-core:1.6.7")
    // Ktor client for Android
    implementation("io.ktor:ktor-client-android:1.6.7")
    // For JSON serialization
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    // For logging
    implementation("io.ktor:ktor-client-logging:1.6.7")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")


    // Koin for Android
    implementation("io.insert-koin:koin-android:3.2.0")

    // Koin Core module
    implementation("io.insert-koin:koin-core:3.2.0")

    // Koin Test
    testImplementation("io.insert-koin:koin-test:3.2.0")

    // MockK for mocking in tests
    testImplementation("io.mockk:mockk:1.12.0")

    // SQLDelight
    implementation("com.squareup.sqldelight:runtime:1.5.3")
    implementation("com.squareup.sqldelight:android-driver:1.5.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-rxjava2:2.6.1")
    implementation("androidx.room:room-rxjava3:2.6.1")
}
kapt {
    correctErrorTypes = true
}