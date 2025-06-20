package io.github.faening.lello.feature.achievement.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.achievement.AchievementViewModel
import io.github.faening.lello.feature.achievement.R as achievementR

@Composable
fun AchievementScreen(
    viewModel: AchievementViewModel,
    onBack: () -> Unit = {}
) {

    LelloTheme {
        AchievementContainer(
            onBack = onBack
        )
    }
}

@Composable
private fun AchievementContainer(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AchievementTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Achievement",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
private fun AchievementTopAppBar(
    onBack: () -> Unit = {}
) {
    val title = stringResource(achievementR.string.achievements_topappbar_title)
    LelloTopAppBar(
        title = TopAppBarTitle(text = title),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun AchievementScreenPreview() {
    LelloTheme {
        AchievementContainer(
            onBack = {}
        )
    }
}