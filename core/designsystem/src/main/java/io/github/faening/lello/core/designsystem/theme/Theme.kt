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
    primary = Brown300,
    onPrimary = Brown900,
    primaryContainer = Brown700,
    onPrimaryContainer = Brown100,

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

    background = Grey800,
    onBackground = Neutral100,
    surface = Grey900,
    onSurface = Neutral50,

    surfaceVariant = Grey700,
    onSurfaceVariant = Neutral300,
    outline = Grey500
)

private val LightColorScheme = lightColorScheme(
    primary = Brown500,
    onPrimary = Neutral50,
    primaryContainer = Brown100,
    onPrimaryContainer = Brown900,

    secondary = Blue500,
    onSecondary = Neutral50,
    secondaryContainer = Blue100,
    onSecondaryContainer = Blue900,

    tertiary = Yellow500,
    onTertiary = Brown900,
    tertiaryContainer = Yellow100,
    onTertiaryContainer = Brown900,

    error = Red500,
    onError = Neutral50,
    errorContainer = Red100,
    onErrorContainer = Red900,

    background = Neutral100,
    onBackground = Grey900,
    surface = Neutral50,
    onSurface = Grey900,

    surfaceVariant = Neutral200,
    onSurfaceVariant = Grey700,
    outline = Grey400
)

@Composable
fun LelloTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Dynamic color is available on Android 12+
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