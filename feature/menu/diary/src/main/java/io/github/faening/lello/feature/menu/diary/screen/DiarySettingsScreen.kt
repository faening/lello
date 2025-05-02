package io.github.faening.lello.feature.menu.diary.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
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
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.diary.Journal
import io.github.faening.lello.feature.menu.diary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiarySettingsScreen(
    onBackClick: () -> Unit
) {



    // Função para atualizar o estado quando o toggle muda
//    val onToggleChanged = { id: String, isEnabled: Boolean ->
//        val index = settingsOptions.indexOfFirst { it.id == id }
//        if (index != -1) {
//            settingsOptions[index] = settingsOptions[index].copy(isEnabled = isEnabled)
//        }
//    }

    Scaffold(
        topBar = {
            DiarySettingsScreenTopAppBar(onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            DiarySettingsScreenTextTop()
            Spacer(modifier = Modifier.height(16.dp))
//            DiarySettingsList(
//                options = settingsOptions,
//                onToggleChanged = onToggleChanged
//            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiarySettingsScreenTopAppBar(
    onBackClick: () -> Unit
) {
    val toolbarTitle = R.string.toolbar_action_settings_title
    val toolbarBackActionDescription = stringResource(R.string.toolbar_action_settings_go_back)

    LelloTopAppBar(
        title = TopAppBarTitle(textRes = toolbarTitle),
        navigateUp = TopAppBarAction(
            icon = LelloIcons.ArrowBack,
            contentDescription = toolbarBackActionDescription,
            onClick = { onBackClick() }
        )
    )
}

@Composable
private fun DiarySettingsScreenTextTop() {
    val topMessage = stringResource(R.string.settings_top_message)

    Text(
        text = topMessage,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
private fun DiarySettingsList(
    options: List<Journal>,
    onToggleChanged: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(options) { option ->
            DiarySettingsOptionCard(
                option = option,
                onToggleChanged = onToggleChanged
            )
        }
    }
}


@Composable
private fun DiarySettingsOptionCard(
    option: Journal,
    onToggleChanged: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                Icon(
//                    imageVector = option.icon,
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.primary
//                )
//                Text(
//                    text = option.title,
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//
//            Switch(
//                checked = option.isEnabled,
//                onCheckedChange = { isChecked ->
//                    onToggleChanged(option.id, isChecked)
//                }
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiarySettingsScreePreview() {
    LelloTheme(darkTheme = false) {
        DiarySettingsScreen(
            onBackClick = {}
        )
    }
}