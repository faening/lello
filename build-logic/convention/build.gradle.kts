import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "io.github.faening.lello.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.room.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.kotlin.gradle.compose.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "lello.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "lello.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplicationComposeUI") {
            id = "lello.android.application.compose.ui"
            implementationClass = "AndroidApplicationComposeUIConventionPlugin"
        }
        register("androidFeature") {
            id = "lello.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibrary") {
            id = "lello.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "lello.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibraryComposeUI") {
            id = "lello.android.library.compose.ui"
            implementationClass = "AndroidLibraryComposeUIConventionPlugin"
        }
        register("firebaseAuthentication") {
            id = "lello.firebase.authentication"
            implementationClass = "FirebaseAuthenticationConventionPlugin"
        }
        register("hilt") {
            id = "lello.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("workManager") {
            id = "lello.work.manager"
            implementationClass = "WorkManagerConventionPlugin"
        }
        register("androidRoom") {
            id = "lello.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "lello.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}