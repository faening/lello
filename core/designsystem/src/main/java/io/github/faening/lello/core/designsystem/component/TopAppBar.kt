package io.github.faening.lello.core.designsystem.component

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme

data class TopAppBarAction(
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit = {}
)

data class TopAppBarTitle(
    @StringRes val textRes: Int? = null,
    val text: String? = null,
    val style: TextStyle? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloTopAppBar(
    title: TopAppBarTitle? = null,
    navigationAction: TopAppBarAction? = null,
    actions: List<TopAppBarAction> = emptyList(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        scrolledContainerColor = MaterialTheme.colorScheme.background
    )
) {
    CenterAlignedTopAppBar(
        title = {
            title?.let {
                Text(
                    text = when {
                        it.textRes != null -> stringResource(id = it.textRes)
                        it.text != null -> it.text
                        else -> ""
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        navigationIcon = {
            navigationAction?.let {
                IconButton(onClick = it.onClick) {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.contentDescription,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        actions = {
            actions.forEach { action ->
                IconButton(onClick = action.onClick) {
                    Icon(
                        imageVector = action.icon,
                        contentDescription = action.contentDescription,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier.testTag("lelloTopAppBar"),
    )
}

// Atualizar os previews para usar a nova API
@OptIn(ExperimentalMaterial3Api::class)
@Preview("TopAppBar Light - Completa")
@Composable
private fun LelloTopAppBarPreview() {
    LelloTheme(darkTheme = false) {
        LelloTopAppBar(
            title = TopAppBarTitle(textRes = android.R.string.untitled),
            navigationAction = TopAppBarAction(
                icon = LelloIcons.ArrowBack,
                contentDescription = "Navigation icon"
            ),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Action icon"
                )
            ),
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
            navigationAction = TopAppBarAction(
                icon = LelloIcons.ArrowBack,
                contentDescription = "Voltar"
            ),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            )
        )
    }
}
