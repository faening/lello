package io.github.faening.lello.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

enum class LelloColorScheme(
    private val lightScheme: ColorScheme,
    private val darkScheme: ColorScheme
) {
    DEFAULT(
        lightScheme = lightColorScheme(
            primary               = Yellow500,
            onPrimary             = Grey500,
            primaryContainer      = Yellow50,
            onPrimaryContainer    = Grey500,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Yellow50,
            onBackground          = Grey500,
            outline               = Grey100
        ),

        darkScheme = darkColorScheme(
            primary               = Yellow500,
            onPrimary             = Grey500,
            primaryContainer      = Grey500,
            onPrimaryContainer    = Grey50,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Grey500,
            onBackground          = Grey50,
            outline               = Grey50
        )
    ),

    INVERSE(
        lightScheme = lightColorScheme(
            primary               = Yellow600,
            onPrimary             = Grey500,
            primaryContainer      = Yellow500,
            onPrimaryContainer    = Grey500,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Yellow500,
            onBackground          = Grey500,
            outline               = Grey100
        ),

        darkScheme = darkColorScheme(
            primary               = Yellow600,
            onPrimary             = Yellow50,
            primaryContainer      = Yellow300,
            onPrimaryContainer    = Yellow50,

            secondary             = Blue300,
            onSecondary           = Blue900,
            secondaryContainer    = Blue700,
            onSecondaryContainer  = Blue100,

            tertiary              = Yellow300,
            onTertiary            = Brown900,
            tertiaryContainer     = Brown700,
            onTertiaryContainer   = Yellow100,

            error                 = Red300,
            onError               = Red900,
            errorContainer        = Red700,
            onErrorContainer      = Red100,

            surface               = Grey500,
            onSurface             = Neutral100,
            surfaceVariant        = Neutral200,
            onSurfaceVariant      = Neutral300,

            background            = Grey500,
            onBackground          = Grey500,
            outline               = Neutral400
        )
    ),

    AQUAMARINE(
        lightScheme = lightColorScheme(
            primary               = Aquamarine500,
            onPrimary             = Grey500,
            primaryContainer      = Yellow50,
            onPrimaryContainer    = Grey500,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Yellow50,
            onBackground          = Grey500,
            outline               = Grey100
        ),

        darkScheme = darkColorScheme(
            primary               = Aquamarine500,
            onPrimary             = Grey500,
            primaryContainer      = Grey500,
            onPrimaryContainer    = Grey50,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Grey500,
            onBackground          = Grey50,
            outline               = Grey50
        )
    ),

    BLUE(
        lightScheme = lightColorScheme(
            primary               = Blue500,
            onPrimary             = Grey50,
            primaryContainer      = Yellow50,
            onPrimaryContainer    = Grey500,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Yellow50,
            onBackground          = Grey500,
            outline               = Grey100
        ),

        darkScheme = darkColorScheme(
            primary               = Blue500,
            onPrimary             = Grey500,
            primaryContainer      = Grey500,
            onPrimaryContainer    = Grey50,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Grey500,
            onBackground          = Grey50,
            outline               = Grey50
        )
    ),

    ORANGE(
        lightScheme = lightColorScheme(
            primary               = Orange500,
            onPrimary             = Grey500,
            primaryContainer      = Yellow50,
            onPrimaryContainer    = Grey500,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Yellow50,
            onBackground          = Grey500,
            outline               = Grey100
        ),

        darkScheme = darkColorScheme(
            primary               = Orange500,
            onPrimary             = Grey500,
            primaryContainer      = Grey500,
            onPrimaryContainer    = Grey50,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Grey500,
            onBackground          = Grey50,
            outline               = Grey50
        )
    ),

    RED(
        lightScheme = lightColorScheme(
            primary               = Red500,
            onPrimary             = Grey50,
            primaryContainer      = Yellow50,
            onPrimaryContainer    = Grey500,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Yellow50,
            onBackground          = Grey500,
            outline               = Grey100
        ),

        darkScheme = darkColorScheme(
            primary               = Red500,
            onPrimary             = Grey50,
            primaryContainer      = Grey500,
            onPrimaryContainer    = Grey50,

            secondary             = Grey500,
            onSecondary           = Grey50,
            secondaryContainer    = Grey100,
            onSecondaryContainer  = Grey900,

            tertiary              = Yellow600,
            onTertiary            = Yellow50,
            tertiaryContainer     = Yellow100,
            onTertiaryContainer   = Yellow900,

            error                 = Red500,
            onError               = Red50,
            errorContainer        = Red100,
            onErrorContainer      = Red900,

            surface               = Yellow50,
            onSurface             = Grey500,
            surfaceVariant        = Grey100,
            onSurfaceVariant      = Grey300,

            background            = Grey500,
            onBackground          = Grey50,
            outline               = Grey50
        )
    );

    fun getScheme(darkTheme: Boolean): ColorScheme = if (darkTheme) darkScheme else lightScheme
}

@Composable
fun LelloTheme(
    scheme: LelloColorScheme = LelloColorScheme.DEFAULT,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = scheme.getScheme(darkTheme),
        typography   = Typography,
        content      = content
    )
}