package io.github.faening.lello.feature.achievement.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.audio.AudioManager
import io.github.faening.lello.core.audio.AudioTrack
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.achievement.AchievementViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
fun AchievementScreen(
    viewModel: AchievementViewModel,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var isMuted by remember { mutableStateOf(false) }
    val vitality by viewModel.vitality.collectAsState()

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
            vitality = vitality,
            isMuted = isMuted,
            onMuteToggle = { isMuted = !isMuted },
            onBack = onBack
        )
    }
}

@Composable
private fun AchievementContainer(
    vitality: Int,
    isMuted: Boolean,
    onMuteToggle: () -> Unit,
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = designsystemR.drawable.img_achievements),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                AchievementTopAppBar(
                    isMuted = isMuted,
                    onMuteToggle = onMuteToggle,
                    onBack = onBack
                )
            }
        ) { paddingValues ->
            AchievementContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun AchievementContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // No topo, adicionar informações de vitalidade e barra de cansaço


        // Mascote aqui
        val mascot = LelloIcons.customIcon(designsystemR.drawable.mascot_lello_bard)

        // Lojas aqui
        // 2 icones na vertical
        val iconshop = LelloIcons.customIcon(designsystemR.drawable.ic_shop)
        val iconInventory = LelloIcons.customIcon(designsystemR.drawable.ic_inventory)
    }
}

@Composable
private fun AchievementTopAppBar(
    isMuted: Boolean,
    onMuteToggle: () -> Unit,
    onBack: () -> Unit = {}
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Lello"),
        navigateUp = TopAppBarAction(onClick = onBack),
        actions = listOf(
            TopAppBarAction(
                icon = if (isMuted) {
                    LelloIcons.customIcon(designsystemR.drawable.ic_sound_off)
                } else {
                    LelloIcons.customIcon(designsystemR.drawable.ic_sound)
                },
                contentDescription = "Icone de Áudio",
                onClick = onMuteToggle
            )
        ),
        backgroundColor = Color.Transparent
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
            vitality = 50,
            isMuted = false,
            onMuteToggle = {},
            onBack = {}
        )
    }
}