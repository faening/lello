package io.github.faening.lello.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension

/**
 * Configura os build types para a aplicação e bibliotecas
 */
fun configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
            }
            getByName("release") {
                isMinifyEnabled = false
            }
        }
    }
}

/**
 * Adiciona configurações específicas para o módulo de aplicação
 */
fun configureApplicationBuildTypes(
    applicationExtension: ApplicationExtension,
) {
    applicationExtension.apply {
        buildTypes {
            getByName("debug") {
                // Esta parte só funciona em módulos de aplicação
                applicationIdSuffix = ".debug"

                // Outras configurações específicas para o módulo da aplicação
                versionNameSuffix = "-debug"
            }

            getByName("release") {
                // Configurações específicas para release no módulo da aplicação
                // Por exemplo: signingConfig = signingConfigs.getByName("release")
            }
        }
    }
}