plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.firebaseconnector"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.firebaseconnector"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-auth:22.2.0")

    //J-UNIT INSTALLATION
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation ("androidx.test.espresso:espresso-intents:3.5.1")
    testImplementation ("androidx.test.uiautomator:uiautomator:2.2.0")
    testImplementation ("androidx.test:rules:1.5.0")
    testImplementation ("androidx.test:runner:1.5.2")



    //UNIT TEST
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation ("androidx.test.espresso:espresso-intents:3.5.1")
    testImplementation ("androidx.test.uiautomator:uiautomator:2.2.0")
    testImplementation ("androidx.test:rules:1.5.0")
    testImplementation ("androidx.test:runner:1.5.2")

    //INSTRUMENTED TESTS
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation ("androidx.test.uiautomator:uiautomator:2.2.0")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test:runner:1.5.2")


    implementation ("com.google.android.gms:play-services-maps:17.0.1")
	implementation ("com.google.android.gms:play-services-location:21.0.1")

	//Google Maps services
	implementation("com.google.maps:google-maps-services:2.2.0")

	implementation ("org.slf4j:slf4j-simple:1.7.25")
    //androidTestImplementation("junit:junit:4.12")


}
