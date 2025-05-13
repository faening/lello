package io.github.faening.lello.feature.menu.achievement.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.feature.menu.achievement.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen(
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AchievementScreenTopAppBar(onBack = onBack)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AchievementScreenTopAppBar(
    onBack: () -> Unit = {}
) {
    val title = R.string.achievements_topappbar_title
    LelloTopAppBar(
        title = TopAppBarTitle(textRes = title)
    )
}