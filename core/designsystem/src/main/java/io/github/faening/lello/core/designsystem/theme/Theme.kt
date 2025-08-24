package io.github.faening.lello.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Yellow500,
    onPrimary = Grey500,
    primaryContainer = Yellow300,
    onPrimaryContainer = Grey300,
    inversePrimary = Yellow500,

    secondary = Grey500,
    onSecondary = Grey50,
    secondaryContainer = Grey100,
    onSecondaryContainer = Grey700,

    tertiary = Yellow600,
    onTertiary = Yellow50,
    tertiaryContainer = Yellow100,
    onTertiaryContainer = Yellow900,

    error = Red500,
    onError = Grey50,
    errorContainer = Red100,
    onErrorContainer = Red700,

    surface = Yellow50,
    onSurface = Grey500,
    surfaceVariant = Grey100,
    onSurfaceVariant = Grey300,
    inverseSurface = Grey500,
    inverseOnSurface = Yellow50,

    background = Yellow50,
    onBackground = Grey500,
    outline = Grey500,
    outlineVariant = Grey300,
    scrim = Grey500
)

private val DarkColorScheme = darkColorScheme(
    primary = Yellow500,
    onPrimary = Grey50,
    primaryContainer = Grey700,
    onPrimaryContainer = Yellow50,
    inversePrimary = Yellow500,

    secondary = Grey50,
    onSecondary = Grey500,
    secondaryContainer = Grey100,
    onSecondaryContainer = Grey100,

    tertiary = Yellow600,
    onTertiary = Grey500,
    tertiaryContainer = Grey600,
    onTertiaryContainer = Yellow200,

    error = Red500,
    onError = Grey50,
    errorContainer = Red100,
    onErrorContainer = Red700,

    surface = Yellow50,
    onSurface = Grey500,
    surfaceVariant = Grey100,
    onSurfaceVariant = Grey300,
    inverseSurface = Yellow100,
    inverseOnSurface = Grey700,

    background = Grey500,
    onBackground = Grey50,
    outline = Grey900,
    outlineVariant = Grey300,
    scrim = Grey100
)

/**
 * O `MoodColor` é um enum que define variações de cor de humor para o tema, permitindo personalizar a cor principal conforme o contexto.
 *
 * Utilize `MoodColor` para alterar dinamicamente a cor principal do tema, escolhendo entre opções pré-definidas para claro e escuro.
 */
enum class MoodColor(
    val lightColor: Color,
    val darkColor: Color
) {
    DEFAULT(Yellow500, Yellow500),
    INVERSE(Yellow600, Yellow600),
    AQUAMARINE(Aquamarine500, Aquamarine500),
    BLUE(Blue500, Blue500),
    ORANGE(Orange500, Orange500),
    RED(Red500, Red500);

    fun getColor(isDark: Boolean): Color = if (isDark) darkColor else lightColor
}

/**
 * `LocalMoodColors` é um composition local que fornece o valor atual de `MoodColor` para a árvore de composables.
 *
 * Permite compartilhar e acessar dinamicamente o "humor" de cor do tema sem precisar passar explicitamente como parâmetro.
 * O valor padrão é `MoodColor.DEFAULT`, utilizado quando nenhum valor foi definido.
 */
val LocalMoodColors = staticCompositionLocalOf { MoodColor.DEFAULT }

@Composable
fun LelloTheme(
    moodColor: MoodColor = MoodColor.DEFAULT,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val baseColorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val colorScheme = baseColorScheme.copy(
        primary = moodColor.getColor(darkTheme)
    )

    CompositionLocalProvider(LocalMoodColors provides moodColor) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = LelloTypography(),
            shapes = LelloShapes,
            content = content
        )
    }
}

/**
 * Objeto utilitário para acessar propriedades do tema Lello em composables.
 *
 * Use `LelloTheme` para obter a paleta de cores (`colorScheme`), o tipo de cor de humor (`moodColor`) e a cor de humor atual
 * (`currentMoodColor`) dentro de componentes composables.
 *
 * Exemplo de uso:
 * val corPrimaria = LelloTheme.colorScheme.primary
 * val mood = LelloTheme.moodColor
 * val corDeHumorAtual = LelloTheme.currentMoodColor
 *
 * Para aplicar o tema, utilize a função composable LelloTheme(...) no topo da árvore de UI.
 */
object LelloTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val moodColor: MoodColor
        @Composable
        @ReadOnlyComposable
        get() = LocalMoodColors.current

    val currentMoodColor: Color
        @Composable
        @ReadOnlyComposable
        get() = moodColor.getColor(colorScheme.primary == DarkColorScheme.primary)
}