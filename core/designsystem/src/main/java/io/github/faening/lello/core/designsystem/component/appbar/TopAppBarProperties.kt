package io.github.faening.lello.core.designsystem.component.appbar

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.MoodColor

@Composable
internal fun TopAppBarTitle(
    title: TopAppBarTitle? = null,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme
) {
    title?.let {
        Text(
            text = it.text
                ?: it.textRes?.let { id -> stringResource(id) }
                ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = it.style ?: MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = TopAppBarProperties.titleTextColor(colorScheme, moodColor)
        )
    }
}

@Composable
internal fun TopAppBarNavigationIcon(
    navigateUp: TopAppBarAction? = null,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme
) {
    navigateUp?.let {
        Box(
            modifier = Modifier.padding(start = Dimension.spacingSmall)
        ) {
            TopAppBarActionButton(
                action = it.also {
                    it.icon = LelloIcons.customIcon(R.drawable.ic_arrow_large_left)
                    it.contentDescription = "Voltar"
                },
                moodColor = moodColor,
                colorScheme = colorScheme,
            )
        }
    }
}

@Composable
internal fun TopAppBarActionIcon(
    actions: List<TopAppBarAction> = emptyList(),
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme
) {
    actions.forEachIndexed { index, action ->
        Box(
            modifier = Modifier.padding(end = Dimension.spacingSmall)
        ) {
            TopAppBarActionButton(
                action = action,
                moodColor = moodColor,
                colorScheme = colorScheme,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarActionButton(
    action: TopAppBarAction,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme
) {
    Box(modifier = Modifier) {
        Surface(
            modifier = Modifier.size(Dimension.heightButtonSmall),
            shape = LelloShape.buttonShape,
            color = TopAppBarProperties.actionButtonBackgroundColor(colorScheme, moodColor),
            onClick = action.onClick
        ) {
            Box(
                modifier = Modifier.padding(Dimension.paddingComponentSmall),
                contentAlignment = Alignment.Center
            ) {
                action.icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = action.contentDescription,
                        tint = TopAppBarProperties.contentColor(moodColor),
                        modifier = Modifier.size(Dimension.iconSizeSmall)
                    )
                }
            }
        }
    }
}

internal object TopAppBarProperties {
    @Composable
    fun titleTextColor(colorScheme: ColorScheme, moodColor: MoodColor): Color {
        return colorScheme.onBackground
//        return if (moodColor == MoodColor.INVERSE || !isSystemInDarkTheme()) {
//            colorScheme.onBackground
//        } else {
//            colorScheme.background
//        }
    }

    @Composable
    fun imageColor(colorScheme: ColorScheme, moodColor: MoodColor): ColorFilter {
        return ColorFilter.tint(colorScheme.onBackground)
//        return if (moodColor == MoodColor.INVERSE || !isSystemInDarkTheme()) {
//            ColorFilter.tint(colorScheme.onBackground)
//        } else {
//            ColorFilter.tint(colorScheme.background)
//        }
    }

    @Composable
    fun backgroundColor(colorScheme: ColorScheme, moodColor: MoodColor): Color {
        return when (moodColor) {
            MoodColor.INVERSE -> colorScheme.inversePrimary
            else -> colorScheme.background
        }
    }

    @Composable
    fun contentColor(moodColor: MoodColor): Color = when {
        moodColor in listOf(
            MoodColor.SECONDARY,
            MoodColor.BLUE,
            MoodColor.RED,
            MoodColor.SECONDARY) -> MaterialTheme.colorScheme.onSecondary
        else -> MaterialTheme.colorScheme.onSurface
    }

    @Composable
    fun actionButtonBackgroundColor(colorScheme: ColorScheme, moodColor: MoodColor): Color {
        val effectiveColorScheme = moodColor.let { mood ->
            colorScheme.copy(primary = mood.getColor(false))
        }

        return when (moodColor) {
            MoodColor.INVERSE -> effectiveColorScheme.tertiary
            else -> effectiveColorScheme.primary
        }
    }
}

data class TopAppBarTitle(
    @StringRes val textRes: Int? = null,
    val text: String? = null,
    val style: TextStyle? = null
)

data class TopAppBarAction(
    var icon: ImageVector? = null,
    var contentDescription: String = "",
    val onClick: () -> Unit = {}
)