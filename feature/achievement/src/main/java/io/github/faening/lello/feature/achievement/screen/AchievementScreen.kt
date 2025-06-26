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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.audio.AudioManager
import io.github.faening.lello.core.audio.AudioTrack
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.achievement.AchievementViewModel
import io.github.faening.lello.feature.achievement.R as achievementR
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
fun AchievementScreen(
    viewModel: AchievementViewModel,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var isMuted by remember { mutableStateOf(false) }

    // Inicia a música ao entrar, para ao sair
    DisposableEffect(isMuted) {
        if (isMuted) {
            AudioManager.stop()
        } else {
            AudioManager.play(context, AudioTrack.MUSIC_SOFT_BACKGROUND)
        }
        onDispose { AudioManager.stop() }
    }

    LelloTheme {
        AchievementContainer(
            isMuted = isMuted,
            onMuteToggle = { isMuted = !isMuted },
            onBack = onBack
        )
    }
}

@Composable
private fun AchievementContainer(
    isMuted: Boolean,
    onMuteToggle: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AchievementTopAppBar(
                isMuted = isMuted,
                onMuteToggle = onMuteToggle,
                onBack = onBack
            )
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
    isMuted: Boolean,
    onMuteToggle: () -> Unit,
    onBack: () -> Unit = {}
) {
    val title = stringResource(achievementR.string.achievements_topappbar_title)
    LelloTopAppBar(
        title = TopAppBarTitle(text = title),
        navigateUp = TopAppBarAction(onClick = onBack),
        actions = listOf(
            TopAppBarAction(
                icon = if (isMuted) {
                    LelloIcons.customIcon(designsystemR.drawable.ic_sound)
                } else {
                    LelloIcons.customIcon(designsystemR.drawable.ic_sound_off)
                },
                contentDescription = "Icone de Áudio",
                onClick = onMuteToggle
            )
        )
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
            isMuted = false,
            onMuteToggle = {},
            onBack = {}
        )
    }
}