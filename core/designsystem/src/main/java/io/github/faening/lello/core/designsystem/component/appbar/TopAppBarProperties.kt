package io.github.faening.lello.core.designsystem.component.appbar

import androidx.annotation.StringRes
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
import io.github.faening.lello.core.designsystem.theme.Aquamarine600
import io.github.faening.lello.core.designsystem.theme.Blue600
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.Grey700
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.designsystem.theme.Orange600
import io.github.faening.lello.core.designsystem.theme.Red600
import io.github.faening.lello.core.designsystem.theme.Yellow600
import io.github.faening.lello.core.designsystem.theme.Yellow700

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
            style = it.style ?: MaterialTheme.typography.bodyLarge.copy(
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
                    it.icon = LelloIcons.customIcon(R.drawable.ic_arrow_left_lg_outlined)
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
        return when (moodColor) {
            MoodColor.DEFAULT -> Grey500
            MoodColor.INVERSE -> Grey500
            MoodColor.AQUAMARINE -> Grey500
            MoodColor.BLUE -> MaterialTheme.colorScheme.onSecondary
            MoodColor.ORANGE -> Grey500
            MoodColor.RED -> MaterialTheme.colorScheme.onSecondary
            MoodColor.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        }
    }

    @Composable
    fun imageColor(colorScheme: ColorScheme, moodColor: MoodColor): ColorFilter {
        return when (moodColor) {
            MoodColor.DEFAULT -> ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            MoodColor.INVERSE -> ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            MoodColor.AQUAMARINE -> ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            MoodColor.BLUE -> ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
            MoodColor.ORANGE -> ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            MoodColor.RED -> ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
            MoodColor.SECONDARY -> ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
        }
    }

    @Composable
    fun backgroundColor(colorScheme: ColorScheme, moodColor: MoodColor): Color {
        val effectiveColorScheme = moodColor.let { mood ->
            colorScheme.copy(primary = mood.getColor(false))
        }
        return effectiveColorScheme.primary
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
        return when (moodColor) {
            MoodColor.DEFAULT -> Yellow600
            MoodColor.INVERSE -> Yellow700
            MoodColor.AQUAMARINE -> Aquamarine600
            MoodColor.BLUE -> Blue600
            MoodColor.ORANGE -> Orange600
            MoodColor.RED -> Red600
            MoodColor.SECONDARY -> Grey700
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