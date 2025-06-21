package io.github.faening.lello.core.designsystem.component.appbar

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.R as designsystemR
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloImageTopAppBar(
    imageResId: Int = designsystemR.drawable.ic_lello_logo,
    navigateUp: TopAppBarAction? = null,
    actions: List<TopAppBarAction> = emptyList(),
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        title = {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Logo do Lello",
                modifier = Modifier
                    .height(40.dp), // ajuste conforme sua logo
                contentScale = ContentScale.Fit
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Default Color - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LelloImageTopAppBarPreview() {
    LelloTheme(scheme = LelloColorScheme.DEFAULT) {
        LelloImageTopAppBar(
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
        )
    }
}