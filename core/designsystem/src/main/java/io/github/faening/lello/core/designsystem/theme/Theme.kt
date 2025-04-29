package io.github.faening.lello.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    // --- Cores Primárias ---
    primary = Yellow500,           // Cor principal do aplicativo, usada para elementos de ação (botões, interruptores, sliders)
    onPrimary = Yellow50,            // Cor do texto/ícones que aparece SOBRE elementos que usam a cor primary
    primaryContainer = Yellow300,  // Cor para containers/superfícies relacionadas à cor primária, com menor ênfase
    onPrimaryContainer = Yellow50,   // Cor do texto/ícones que aparece SOBRE containers primários

    secondary = Blue300,
    onSecondary = Blue900,
    secondaryContainer = Blue700,
    onSecondaryContainer = Blue100,

    tertiary = Yellow300,
    onTertiary = Brown900,
    tertiaryContainer = Brown700,
    onTertiaryContainer = Yellow100,

    error = Red300,
    onError = Red900,
    errorContainer = Red700,
    onErrorContainer = Red100,

    // --- Cores de Fundo e Superfície ---
    background = Grey500,       // Cor de fundo principal do aplicativo
    onBackground = Grey500,     // Cor do texto/ícones padrão que aparece SOBRE o fundo do aplicativo
    surface = Grey500,          // Cor para cartões, folhas de diálogo e outras superfícies elevadas
    onSurface = Neutral100,     // Cor do texto/ícones que aparece SOBRE superfícies

    // --- Cores de Variante e Contorno ---
    surfaceVariant = Neutral200,      // Variante sutil da superfície para diferenciar áreas da interface
    onSurfaceVariant = Neutral300,    // Cor para texto/ícones secundários sobre variantes de superfície
    outline = Neutral400,                // Cor para bordas e divisores, definindo limites entre elementos
)

private val LightColorScheme = lightColorScheme(
    // --- Cores Primárias ---
    primary = Yellow500,            // Cor principal do aplicativo, usada para elementos de ação (botões, interruptores, sliders)
    onPrimary = Grey500,            // Cor do texto/ícones que aparece SOBRE elementos que usam a cor primary
    primaryContainer = Yellow300,   // Cor para containers/superfícies relacionadas à cor primária, com menor ênfase
    onPrimaryContainer = Grey300,   // Cor do texto/ícones que aparece SOBRE containers primários

    // --- Cores Secundárias ---
    secondary = Blue500,              // Cor secundária para elementos menos proeminentes, mas ainda importantes
    onSecondary = Neutral50,          // Cor do texto/ícones que aparece SOBRE elementos que usam a cor secondary
    secondaryContainer = Blue100,     // Containers secundários, menos enfáticos que os primários
    onSecondaryContainer = Blue900,   // Cor do texto/ícones que aparece SOBRE containers secundários

    // --- Cores Terciárias ---
    tertiary = Yellow500,             // Cor de acento para elementos distintos, complementando a primária e secundária
    onTertiary = Brown900,            // Cor do texto/ícones que aparece SOBRE elementos que usam a cor tertiary
    tertiaryContainer = Yellow100,    // Containers terciários para agrupar conteúdo relacionado ao tema terciário
    onTertiaryContainer = Brown900,   // Cor do texto/ícones que aparece SOBRE containers terciários

    // --- Cores de Erro ---
    error = Red500,              // Cor para indicar estados de erro ou ações destrutivas
    onError = Neutral50,         // Cor do texto/ícones que aparece SOBRE elementos que usam a cor error
    errorContainer = Red100,     // Containers para mensagens de erro ou avisos, menos intenso que error
    onErrorContainer = Red900,   // Cor do texto/ícones que aparece SOBRE containers de erro

    // --- Cores de Fundo e Superfície ---
    background = Yellow50,      // Cor de fundo principal do aplicativo
    onBackground = Grey500,     // Cor do texto/ícones padrão que aparece SOBRE o fundo do aplicativo
    surface = Yellow50,          // Cor para cartões, folhas de diálogo e outras superfícies elevadas
    onSurface = Grey500,        // Cor do texto/ícones que aparece SOBRE superfícies

    // --- Cores de Variante e Contorno ---
    surfaceVariant = Neutral200,   // Variante sutil da superfície para diferenciar áreas da interface
    onSurfaceVariant = Grey300,    // Cor para texto/ícones secundários sobre variantes de superfície
    outline = Grey100,             // Cor para bordas e divisores, definindo limites entre elementos
)

@Composable
fun LelloTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}