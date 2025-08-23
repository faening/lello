package io.github.faening.lello.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

enum class LelloColorScheme(
    private val lightScheme: ColorScheme,
    private val darkScheme: ColorScheme
) {
    DEFAULT(
        lightScheme = lightColorScheme(
            // Cores primárias - Elementos de destaque e identidade visual
            primary = Yellow500,                // Botões, elementos interativos principais
            onPrimary = Grey500,                // Texto/ícones sobre elementos primários
            primaryContainer = Yellow200,       // Containers de elementos primários (botões com fundo)
            onPrimaryContainer = Grey500,       // Texto sobre containers primários

            // Cores secundárias - Elementos de suporte
            secondary = Grey500,                // Botões secundários, elementos de suporte
            onSecondary = Yellow50,             // Texto/ícones sobre elementos secundários
            secondaryContainer = Grey100,       // Containers secundários
            onSecondaryContainer = Grey700,     // Texto sobre containers secundários

            // Cores terciárias - Acentuação visual
            tertiary = Yellow600,               // Elementos de destaque terciários
            onTertiary = Yellow50,              // Texto sobre elementos terciários
            tertiaryContainer = Yellow100,      // Containers terciários (mais leve)
            onTertiaryContainer = Yellow900,    // Texto sobre containers terciários

            // Cores de erro
            error = Red500,                     // Elementos de erro, alertas importantes
            onError = Grey50,                   // Texto sobre elementos de erro
            errorContainer = Red100,            // Containers de erro (mais suaves)
            onErrorContainer = Red900,          // Texto sobre containers de erro

            // Cores de superfície
            surface = Yellow50,                 // Superfícies de cartões, dialogs
            onSurface = Grey500,                // Texto principal sobre superfícies
            surfaceVariant = Yellow100,         // Variações de superfície
            onSurfaceVariant = Grey200,         // Texto sobre variações de superfície
            inverseSurface = Grey500,           // Superfície invertida
            inverseOnSurface = Yellow50,        // Texto sobre superfície invertida

            // Cores de fundo e contorno
            background = Yellow50,              // Fundo geral da aplicação
            onBackground = Grey500,             // Texto sobre o fundo
            outline = Grey500,                  // Bordas, separadores
            outlineVariant = Grey300            // Variação mais sutil de contorno
        ),

        darkScheme = darkColorScheme(
            // Cores primárias
            primary = Yellow500,                // Mantém a identidade visual no dark mode
            onPrimary = Grey700,                // Texto mais escuro sobre amarelo no dark mode
            primaryContainer = Grey700,         // Containers mais escuros no dark mode
            onPrimaryContainer = Yellow200,     // Texto em tom amarelo claro sobre fundo escuro

            // Cores secundárias
            secondary = Grey300,                // Elementos secundários mais claros no dark
            onSecondary = Grey800,              // Texto escuro sobre elementos secundários
            secondaryContainer = Grey100,       // Containers secundários mais escuros
            onSecondaryContainer = Grey100,     // Texto claro sobre containers escuros

            // Cores terciárias
            tertiary = Yellow500,               // Elementos terciários mais vibrantes
            onTertiary = Grey800,               // Texto escuro sobre elementos terciários
            tertiaryContainer = Grey600,        // Containers terciários escuros
            onTertiaryContainer = Yellow200,    // Texto amarelo claro sobre containers terciários

            // Cores de erro
            error = Red400,                     // Erro ligeiramente mais suave no dark mode
            onError = Grey800,                  // Texto escuro sobre erro
            errorContainer = Red800,            // Container de erro mais escuro
            onErrorContainer = Red100,          // Texto claro sobre container de erro

            // Cores de superfície
            surface = Grey800,                  // Superfícies escuras
            onSurface = Grey50,                 // Texto claro sobre superfícies
            surfaceVariant = Grey700,           // Variações de superfície
            onSurfaceVariant = Grey200,         // Texto sobre variações de superfície
            inverseSurface = Yellow100,         // Superfície invertida (clara)
            inverseOnSurface = Grey700,         // Texto sobre superfície invertida

            // Cores de fundo e contorno
            background = Grey900,               // Fundo mais escuro da aplicação
            onBackground = Grey50,              // Texto claro amarelado sobre fundo
            outline = Grey300,                  // Bordas mais visíveis
            outlineVariant = Grey300            // Variação sutil de contorno
        )
    ),

    INVERSE(
        lightScheme = lightColorScheme(
            primary = Yellow600,
            onPrimary = Grey500,
            primaryContainer = Yellow500,
            onPrimaryContainer = Grey500,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Yellow700,
            onSurfaceVariant = Grey300,

            background = Yellow500,
            onBackground = Grey500,
            outline = Grey500,
            outlineVariant = Grey300
        ),

        darkScheme = darkColorScheme(
            primary = Yellow600,
            onPrimary = Grey500,
            primaryContainer = Yellow300,
            onPrimaryContainer = Yellow50,

            secondary = Blue300,
            onSecondary = Blue900,
            secondaryContainer = Blue700,
            onSecondaryContainer = Blue100,

            tertiary = Yellow300,
            onTertiary = Yellow900,
            tertiaryContainer = Yellow600,
            onTertiaryContainer = Yellow100,

            error = Red300,
            onError = Red900,
            errorContainer = Red700,
            onErrorContainer = Red100,

            surface = Grey500,
            onSurface = Grey50,
            surfaceVariant = Yellow700,
            onSurfaceVariant = Grey50,

            background = Grey500,
            onBackground = Grey500,
            outline = Grey900,
            outlineVariant = Grey300
        )
    ),

    AQUAMARINE(
        lightScheme = lightColorScheme(
            primary = Aquamarine500,
            onPrimary = Grey500,
            primaryContainer = Yellow50,
            onPrimaryContainer = Grey500,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Aquamarine700,
            onSurfaceVariant = Grey300,

            background = Yellow50,
            onBackground = Grey500,
            outline = Grey500
        ),

        darkScheme = darkColorScheme(
            primary = Aquamarine500,
            onPrimary = Grey500,
            primaryContainer = Grey500,
            onPrimaryContainer = Grey50,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Aquamarine700,
            onSurfaceVariant = Grey300,

            background = Grey500,
            onBackground = Grey50,
            outline = Grey900
        )
    ),

    BLUE(
        lightScheme = lightColorScheme(
            primary = Blue500,
            onPrimary = Grey50,
            primaryContainer = Yellow50,
            onPrimaryContainer = Grey500,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Blue700,
            onSurfaceVariant = Grey300,

            background = Yellow50,
            onBackground = Grey500,
            outline = Grey500
        ),

        darkScheme = darkColorScheme(
            primary = Blue500,
            onPrimary = Grey50,
            primaryContainer = Grey500,
            onPrimaryContainer = Grey50,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Blue700,
            onSurfaceVariant = Grey300,

            background = Grey500,
            onBackground = Grey50,
            outline = Grey900
        )
    ),

    ORANGE(
        lightScheme = lightColorScheme(
            primary = Orange500,
            onPrimary = Grey500,
            primaryContainer = Yellow50,
            onPrimaryContainer = Grey500,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Orange700,
            onSurfaceVariant = Grey300,

            background = Yellow50,
            onBackground = Grey500,
            outline = Grey500
        ),

        darkScheme = darkColorScheme(
            primary = Orange500,
            onPrimary = Grey500,
            primaryContainer = Grey500,
            onPrimaryContainer = Grey50,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Orange700,
            onSurfaceVariant = Grey300,

            background = Grey500,
            onBackground = Grey50,
            outline = Grey900
        )
    ),

    RED(
        lightScheme = lightColorScheme(
            primary = Red500,
            onPrimary = Grey50,
            primaryContainer = Yellow50,
            onPrimaryContainer = Grey500,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Red700,
            onSurfaceVariant = Grey300,

            background = Yellow50,
            onBackground = Grey500,
            outline = Grey500
        ),

        darkScheme = darkColorScheme(
            primary = Red500,
            onPrimary = Grey50,
            primaryContainer = Grey500,
            onPrimaryContainer = Grey50,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Red700,
            onSurfaceVariant = Grey300,

            background = Grey500,
            onBackground = Grey50,
            outline = Grey900
        )
    ),

    DARK(
        lightScheme = lightColorScheme(
            primary = Grey500,
            onPrimary = Grey50,
            primaryContainer = Yellow50,
            onPrimaryContainer = Grey500,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Grey500,
            onSurfaceVariant = Grey300,

            background = Yellow50,
            onBackground = Grey500,
            outline = Grey500,
            outlineVariant = Grey200
        ),

        darkScheme = darkColorScheme(
            primary = Red500,
            onPrimary = Grey50,
            primaryContainer = Grey500,
            onPrimaryContainer = Grey50,

            secondary = Grey500,
            onSecondary = Grey50,
            secondaryContainer = Grey100,
            onSecondaryContainer = Grey900,

            tertiary = Yellow600,
            onTertiary = Yellow50,
            tertiaryContainer = Yellow100,
            onTertiaryContainer = Yellow900,

            error = Red500,
            onError = Red50,
            errorContainer = Red100,
            onErrorContainer = Red900,

            surface = Yellow50,
            onSurface = Grey500,
            surfaceVariant = Red700,
            onSurfaceVariant = Grey300,

            background = Grey500,
            onBackground = Grey50,
            outline = Grey900,
            outlineVariant = Grey200
        )
    );

    fun getScheme(darkTheme: Boolean): ColorScheme = if (darkTheme) darkScheme else lightScheme
}

val LocalIsDarkTheme = compositionLocalOf { false }

@Composable
fun LelloTheme(
    scheme: LelloColorScheme = LelloColorScheme.DEFAULT,
    darkTheme: Boolean = false, // isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalIsDarkTheme provides darkTheme
    ) {
        MaterialTheme(
            colorScheme = scheme.getScheme(darkTheme),
            typography = Typography
        ) {
            content()
        }
    }
}