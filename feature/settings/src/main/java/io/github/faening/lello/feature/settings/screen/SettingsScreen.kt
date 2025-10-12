package io.github.faening.lello.feature.settings.screen

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
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.settings.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {

    SettingsScreenContent(

    )
}

@Composable
private fun SettingsScreenContent(
    moodColor: MoodColor = MoodColor.DEFAULT
) {
    LelloTheme {
        Scaffold(
            topBar = {
                TopAppBarSection()
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
                    text = "Blah",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarSection(
    moddColor: MoodColor = MoodColor.INVERSE
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text= "Mais recursos"),
        moodColor = moddColor
    )
}