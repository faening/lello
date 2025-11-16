package io.github.faening.lello.core.designsystem.component.appbar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloAchievementTopAppBar(
    vitality: Int,
    money: Int,
    navigateUp: TopAppBarAction? = null,
    actions: List<TopAppBarAction> = emptyList(),
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            StatCenter(
                vitality = vitality,
                money = money,
                colorScheme = colorScheme
            )
        },
        navigationIcon = {
            TopAppBarNavigationIcon(
                navigateUp = navigateUp,
                moodColor = moodColor,
                colorScheme = colorScheme
            )
        },
        actions = {
            TopAppBarActionIcon(
                actions = actions,
                moodColor = moodColor,
                colorScheme = colorScheme
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = colorScheme.onSurface,
            titleContentColor = colorScheme.onSurface,
            actionIconContentColor = colorScheme.onSurface
        ),
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
    )
}

@Composable
private fun StatCenter(
    vitality: Int,
    money: Int,
    colorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimension.spacingMedium),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        StatPill(
            icon = LelloIcons.Graphic.Heart.imageVector,
            text = vitality.toString(),
            colorScheme = colorScheme
        )
        StatPill(
            icon = LelloIcons.Graphic.Coin.imageVector,
            text = money.toString(),
            colorScheme = colorScheme
        )
    }
}

@Composable
private fun StatPill(
    icon: ImageVector,
    text: String,
    colorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    Surface(
        color = colorScheme.tertiary,
        contentColor = Color.Transparent,
        shape = LelloShape.buttonShape,
        modifier = modifier
            .height(Dimension.heightButtonSmall)
            .widthIn(min = 80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(Dimension.heightButtonSmall)
                .padding(horizontal = Dimension.spacingMedium)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(Dimension.iconSizeDefault)
            )
            Spacer(modifier = Modifier.padding(end = Dimension.spacingExtraSmall))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

// region: Previews

@Preview(
    name = "Primary",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloAchievementTopAppBarPreview_LightTheme() {
    LelloTheme {
        LelloAchievementTopAppBar(
            vitality = 75,
            money = 1200,
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Outlined.Sound.imageVector,
                    contentDescription = "Sound"
                )
            )
        )
    }
}

// endregion: Previews