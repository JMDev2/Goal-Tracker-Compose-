// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Hilt Gradle plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")

    }

}