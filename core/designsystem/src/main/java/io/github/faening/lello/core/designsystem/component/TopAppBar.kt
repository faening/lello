package io.github.faening.lello.core.designsystem.component

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Grey50
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow50
import io.github.faening.lello.core.designsystem.theme.Yellow500
import io.github.faening.lello.core.designsystem.theme.Yellow600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloTopAppBar(
    title: TopAppBarTitle? = null,
    navigateUp: TopAppBarAction? = null,
    actions: List<TopAppBarAction> = emptyList(),
    variant: AppBarVariant = AppBarVariant.Light
) {
    val colors = appBarColorsFor(variant)

    CenterAlignedTopAppBar(
        title = {
            title?.let {
                Text(
                    text = when {
                        it.textRes != null -> stringResource(id = it.textRes)
                        it.text != null -> it.text
                        else -> ""
                    },
                    style = it.style ?: MaterialTheme.typography.titleLarge,
                    color = colors.titleContentColor
                )
            }
        },
        navigationIcon = {
            navigateUp?.let {
                TopAppBarActionButton(
                    action = it.also {
                        it.icon = LelloIcons.customIcon(R.drawable.ic_arrow_large_left)
                        it.contentDescription = "Voltar"
                    },
                    colors = colors,
                )
            }
        },
        actions = {
            actions.forEachIndexed { index, action ->
                TopAppBarActionButton(
                    action = action,
                    colors = colors,
                    contentPadding = if (index < actions.size - 1) {
                        Modifier.padding(end = 8.dp)
                    } else {
                        Modifier
                    }
                )
            }
        },
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarActionButton(
    action: TopAppBarAction,
    colors: TopAppBarColors,
    @SuppressLint("ModifierParameter") contentPadding: Modifier = Modifier
) {
    Box(modifier = contentPadding) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(8.dp),
            color = colors.actionIconContentColor,
            onClick = action.onClick
        ) {
            Box(
                modifier = Modifier.padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (action.icon != null) {
                    Icon(
                        imageVector = action.icon!!,
                        contentDescription = action.contentDescription,
                        tint = colors.navigationIconContentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
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

enum class AppBarVariant {
    Light,
    LightInverse,
    Dark
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun appBarColorsFor(variant: AppBarVariant): TopAppBarColors {
    return when (variant) {
        AppBarVariant.Light -> TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Yellow50,
            navigationIconContentColor = Grey500,
            titleContentColor = Grey500,
            actionIconContentColor = Yellow500
        )

        AppBarVariant.LightInverse -> TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Yellow500,
            navigationIconContentColor = Grey500,
            titleContentColor = Grey500,
            actionIconContentColor = Yellow600
        )

        AppBarVariant.Dark -> TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Grey500,
            navigationIconContentColor = Yellow500,
            titleContentColor = Grey50,
            actionIconContentColor = Yellow500
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("TopAppBar Light - Completa")
@Composable
private fun LelloTopAppBarPreview() {
    LelloTheme(darkTheme = false) {
        LelloTopAppBar(
            title = TopAppBarTitle(textRes = android.R.string.untitled),
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Action icon"
                )
            ),
            variant = AppBarVariant.Light
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("TopAppBar Light - Com Múltiplas Ações")
@Composable
private fun LelloTopAppBarMultipleActionsPreview() {
    LelloTheme(darkTheme = false) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "Múltiplas Ações"),
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            variant = AppBarVariant.LightInverse
        )
    }
}