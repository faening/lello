package io.github.faening.lello.core.designsystem.component.button

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.designsystem.R as designsystemR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloBigestFloatingActionButton(
    icon: ImageVector,
    contentDescription: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier.padding(bottom = Dimension.paddingComponentSmall, end = Dimension.paddingComponentSmall)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .size(Dimension.heightButtonLarge)
                .offset(x = Dimension.shadowOffsetX, y = Dimension.shadowOffsetY)
                .background(
                    color = ButtonProperties.shadowColor(enabled),
                    shape = LelloShape.fabShape
                )
                .matchParentSize()
        )

        FloatingActionButton(
            modifier = Modifier
                .size(Dimension.heightButtonLarge)
                .border(
                    border = BorderStroke(
                        width = Dimension.borderWidthThick,
                        color = ButtonProperties.borderColor(enabled)
                    ),
                    shape = LelloShape.fabShape
                )
                .clickable(enabled = enabled, onClick = onClick),
            containerColor = ButtonProperties.backgroundColor(enabled, colorScheme, moodColor),
            shape = LelloShape.fabShape,
            elevation = ButtonProperties.fabElevation(),
            onClick = { if (enabled) onClick() },
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color.Unspecified,
                modifier = Modifier.size(Dimension.iconSizeExtraLarge)
            )
        }
    }
}

// region: Preview Light Theme

@Preview(
    name = "Disabled",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Disabled() {
    LelloTheme {
        LelloBigestFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_achievements_shop_custom),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Light Theme