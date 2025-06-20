package io.github.faening.lello.core.designsystem.component.appbar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloTopAppBar(
    title: TopAppBarTitle? = null,
    navigateUp: TopAppBarAction? = null,
    actions: List<TopAppBarAction> = emptyList()
) {
    val colorScheme = MaterialTheme.colorScheme

    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        title = {
            TopAppBarTitle(
                title = title,
                colorScheme = colorScheme
            )
        },
        navigationIcon = {
            TopAppBarNavigationIcon(
                navigateUp = navigateUp,
                colorScheme = colorScheme
            )
        },
        actions = {
            TopAppBarActionIcon(
                actions = actions,
                colorScheme = colorScheme
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorScheme.primaryContainer,
            titleContentColor = colorScheme.onPrimaryContainer,
            navigationIconContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.primary
        )
    )
}

@Composable
private fun TopAppBarTitle(
    title: TopAppBarTitle? = null,
    colorScheme: ColorScheme
) {
    title?.let {
        Text(
            text = it.text
                ?: it.textRes?.let { id -> stringResource(id) }
                ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = it.style ?: MaterialTheme.typography.titleLarge,
            color = colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun TopAppBarNavigationIcon(
    navigateUp: TopAppBarAction? = null,
    colorScheme: ColorScheme
) {
    navigateUp?.let {
        Box(modifier = Modifier.padding(start = Dimension.Small)) {
            TopAppBarActionButton(
                action = it.also {
                    it.icon = LelloIcons.customIcon(R.drawable.ic_arrow_large_left)
                    it.contentDescription = "Voltar"
                },
                iconTint = colorScheme.onPrimary,
                background = colorScheme.primary
            )
        }
    }
}

@Composable
private fun TopAppBarActionIcon(
    actions: List<TopAppBarAction> = emptyList(),
    colorScheme: ColorScheme
) {
    actions.forEachIndexed { index, action ->
        Box(
            modifier = Modifier.padding(
                end = Dimension.Small
            )
        ) {
            TopAppBarActionButton(
                action = action,
                iconTint = colorScheme.onPrimary,
                background = colorScheme.primary
            )
        }
    }
}

data class TopAppBarTitle(
    @StringRes val textRes: Int? = null,
    val text: String? = null,
    val style: TextStyle? = null
)

// region: TopAppBar Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Default Color - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LelloTopAppBarDefaultLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "MyAppBar"),
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
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Default Color - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LelloTopAppBarDefaultDarkPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "MyAppBar"),
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
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Aquamarine Color",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LelloTopAppBarAquamarinePreview() {
    LelloTheme(
        scheme = LelloColorScheme.AQUAMARINE
    ) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "MyAppBar"),
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
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "TopAppBarLight - Blue",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LelloTopAppBarBluePreview() {
    LelloTheme(
        scheme = LelloColorScheme.BLUE
    ) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "MyAppBar"),
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
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "TopAppBarLight - Orange",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LelloTopAppBarOrangePreview() {
    LelloTheme(
        scheme = LelloColorScheme.ORANGE
    ) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "MyAppBar"),
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
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "TopAppBarLight - Red",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LelloTopAppBarRedPreview() {
    LelloTheme(
        scheme = LelloColorScheme.RED
    ) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "MyAppBar"),
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
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "TopAppBarLight - Inverse",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LelloTopAppBarInversePreview() {
    LelloTheme(
        scheme = LelloColorScheme.INVERSE
    ) {
        LelloTopAppBar(
            title = TopAppBarTitle(text = "MyAppBar"),
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
        )
    }
}

// endregion