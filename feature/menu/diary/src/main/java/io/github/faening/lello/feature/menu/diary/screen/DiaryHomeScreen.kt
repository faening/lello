package io.github.faening.lello.feature.menu.diary.screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.feature.menu.diary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryHomeScreen(
    onSettingsClick: () -> Unit,
    onEditDiaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { DiaryHomeScreenTopAppBar(onSettingsClick) }
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
                text = "Diaries",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiaryHomeScreenTopAppBar(
    onSettingsClick: () -> Unit
) {
    val toolbarTitle = R.string.home_toolbar_title
    val toolbarActionSettingsDescription = stringResource(R.string.home_toolbar_action_settings_description)

    LelloTopAppBar(
        title = TopAppBarTitle(textRes = toolbarTitle),
        actions = listOf(
            TopAppBarAction(
                icon = LelloIcons.Settings,
                contentDescription = toolbarActionSettingsDescription,
                onClick = { onSettingsClick() }
            )
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DiaryHomeScreenPreview() {
    DiaryHomeScreen(
        onSettingsClick = {},
        onEditDiaryClick = {}
    )
}