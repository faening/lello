package io.github.faening.lello.core.designsystem.component.appbar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloImageTopAppBar(
    imageResId: Int = LelloIcons.Logo.resId,
    contentDescription: String? = "Top App Bar Image",
    navigateUp: TopAppBarAction? = null,
    actions: List<TopAppBarAction> = emptyList(),
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .clip(LelloShape.topAppBarShape),
        title = {
            Image(
                painter = painterResource(imageResId),
                contentDescription = contentDescription,
                modifier = Modifier.height(Dimension.heightButtonSmall),
                contentScale = ContentScale.Fit,
                colorFilter = TopAppBarProperties.imageColor(colorScheme, moodColor)
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
            containerColor = TopAppBarProperties.backgroundColor(colorScheme, moodColor),
            titleContentColor = colorScheme.onBackground,
        )
    )
}

// region: Preview Light Theme

@Preview(
    name = "Primary",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ImageTopAppBar_LightTheme_Primary() {
    LelloTheme {
        LelloImageTopAppBar(
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

@Preview(
    name = "Secondary",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ImageTopAppBar_LightTheme_Secondary() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.SECONDARY,
        )
    }
}

@Preview(
    name = "Inverse",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ImageTopAppBar_LightTheme_Inverse() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.INVERSE,
        )
    }
}

@Preview(
    name = "Aquamarine",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ImageTopAppBar_LightTheme_Aquamarine() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.AQUAMARINE,
        )
    }
}

@Preview(
    name = "Blue",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ImageTopAppBar_LightTheme_Blue() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.BLUE,
        )
    }
}

@Preview(
    name = "Orange",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ImageTopAppBar_LightTheme_Orange() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.ORANGE,
        )
    }
}

@Preview(
    name = "Red",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ImageTopAppBar_LightTheme_Red() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.RED,
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Dark Theme

@Preview(
    name = "Primary",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ImageTopAppBar_DarkTheme_Primary() {
    LelloTheme {
        LelloImageTopAppBar(
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

@Preview(
    name = "Secondary",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ImageTopAppBar_DarkTheme_Secondary() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.SECONDARY,
        )
    }
}

@Preview(
    name = "Inverse",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ImageTopAppBar_DarkTheme_Inverse() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.INVERSE,
        )
    }
}

@Preview(
    name = "Aquamarine",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ImageTopAppBar_DarkTheme_Aquamarine() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.AQUAMARINE,
        )
    }
}

@Preview(
    name = "Blue",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ImageTopAppBar_DarkTheme_Blue() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.BLUE,
        )
    }
}

@Preview(
    name = "Orange",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ImageTopAppBar_DarkTheme_Orange() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.ORANGE,
        )
    }
}

@Preview(
    name = "Red",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ImageTopAppBar_DarkTheme_Red() {
    LelloTheme {
        LelloImageTopAppBar(
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
            moodColor = MoodColor.RED,
        )
    }
}

// endregion: Preview Dark Theme