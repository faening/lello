package io.github.faening.lello.feature.achievement.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.achievement.AchievementViewModel

@Composable
fun AchievementStoreScreen(
    viewModel: AchievementViewModel,
    onBack: () -> Unit = {}
) {

    AchievementStoreScreenContent(
        onBack = onBack
    )
}

@Composable
private fun AchievementStoreScreenContent(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AchievementStoreTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {



        }
    }
}

@Composable
private fun AchievementStoreTopAppBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text= "Loja"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

// region: Previes

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun AchievementStoreScreenPreview_LightMode() {
    LelloTheme {
        AchievementStoreScreenContent(
            onBack = {}
        )
    }
}

// endregion: Previes