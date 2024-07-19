import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.dokka")
    id("kjwt.multiplatform")
}

kotlin {
    sourceSets {
        all {
            // Disable warnings and errors related to these expected @OptIn annotations.
            // See: https://kotlinlang.org/docs/opt-in-requirements.html#module-wide-opt-in
            languageSettings.optIn("kotlin.RequiresOptIn")
            languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            languageSettings.optIn("kotlinx.coroutines.FlowPreview")
            languageSettings.optIn("kotlin.time.ExperimentalTime")
            languageSettings.optIn("-Xexpect-actual-classes")
        }

        val commonMain by getting {
            dependencies {
                // Coroutines
                // https://github.com/Kotlin/kotlinx.coroutines
                implementation(KotlinX.coroutines.core)

                // Serialization
                // https://github.com/Kotlin/kotlinx.serialization
                implementation(KotlinX.serialization.json)

                // Time
                // https://github.com/Kotlin/kotlinx-datetime
                implementation(KotlinX.datetime)

                // SHA Hash Functions
                // https://github.com/KotlinCrypto/hash
                implementation("org.kotlincrypto.hash:sha2:_")

                // HMAC Functions
                // https://github.com/KotlinCrypto/MACs
                implementation("org.kotlincrypto.macs:hmac-sha2:_")

                // Secure Random (CSPRNG)
                // https://github.com/KotlinCrypto/secure-random
                implementation("org.kotlincrypto:secure-random:_")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.jsonwebtoken:jjwt-api:_")
                runtimeOnly("io.jsonwebtoken:jjwt-impl:_")
                runtimeOnly("org.bouncycastle:bcprov-jdk18on:_")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.jsonwebtoken:jjwt-api:_")
                runtimeOnly("io.jsonwebtoken:jjwt-impl:_")
                runtimeOnly("org.bouncycastle:bcprov-jdk18on:_")
            }
        }
    }
}

android {
    compileSdk = LibraryConstants.Android.compileSdkVersion
    namespace = "com.mooncloak.kodetools.kjwt.core"

    defaultConfig {
        minSdk = LibraryConstants.Android.minSdkVersion
        targetSdk = LibraryConstants.Android.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            // Opt-in to experimental compose APIs
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].java.srcDirs("src/androidMain/kotlin")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    sourceSets["test"].java.srcDirs("src/androidTest/kotlin")
    sourceSets["test"].res.srcDirs("src/androidTest/res")
}
