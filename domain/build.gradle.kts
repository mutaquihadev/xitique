plugins {
    //id("de.mannodermaus.android-junit5") version "1.10.0.0"
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
//    id("de.mannodermaus.android-junit5")




//    id("de.mannodermaus.android-junit5") version "1.10.0.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")

}