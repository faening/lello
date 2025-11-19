package io.github.faening.lello.feature.achievement.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.audio.AudioManager
import io.github.faening.lello.core.audio.AudioTrack
import io.github.faening.lello.core.designsystem.component.appbar.LelloAchievementTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.button.LelloBigestFloatingActionButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.achievement.AchievementViewModel

@Composable
fun AchievementScreen(
    viewModel: AchievementViewModel,
    onNavigateToStore: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
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

    AchievementScreenContent(
        vitality = uiState.vitality,
        money = uiState.money,
        isMuted = isMuted,
        onMuteToggle = { isMuted = !isMuted },
        onNavigateToStore = onNavigateToStore,
        onNavigateToInventory = onNavigateToInventory,
        onBack = onBack
    )
}

@Composable
private fun AchievementScreenContent(
    vitality: Int,
    money: Int,
    isMuted: Boolean,
    onMuteToggle: () -> Unit,
    onNavigateToStore: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AchievementScreenBackground()

        Scaffold(
            topBar = {
                AchievementScreenTopAppBar(
                    vitality = vitality,
                    money = money,
                    isMuted = isMuted,
                    onMuteToggle = onMuteToggle,
                    onBack = onBack
                )
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                val mascot = LelloIcons.Graphic.AchievementsMascotLelloBard.imageVector
                Icon(
                    imageVector = mascot,
                    contentDescription = "Mascote",
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.Center),
                    tint = Color.Unspecified
                )

                AchievementShortcuts(
                    onNavigateToStore = onNavigateToStore,
                    onNavigateToInventory = onNavigateToInventory,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(Dimension.spacingRegular)
                )
            }
        }
    }
}

@Composable
private fun AchievementScreenBackground() {
    Image(
        painter = painterResource(LelloIcons.Graphic.AchievementsBackgroundForest.resId),
        contentDescription = "Background da tela de conquistas",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun AchievementScreenTopAppBar(
    vitality: Int,
    money: Int,
    isMuted: Boolean,
    onMuteToggle: () -> Unit,
    onBack: () -> Unit = {}
) {
    LelloAchievementTopAppBar(
        vitality = vitality,
        money = money,
        navigateUp = TopAppBarAction(onClick = onBack),
        onToggleSound = onMuteToggle,
        soundIcon = if (isMuted) {
            LelloIcons.customIcon(LelloIcons.Outlined.SoundOff.resId)
        } else {
            LelloIcons.customIcon(LelloIcons.Outlined.Sound.resId)
        }
    )
}

@Composable
private fun AchievementShortcuts(
    onNavigateToStore: () -> Unit,
    onNavigateToInventory: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingRegular),
        horizontalAlignment = Alignment.End
    ) {
        LelloBigestFloatingActionButton(
            icon = LelloIcons.Graphic.AchievementsInventory.imageVector,
            contentDescription = "Inventário",
            onClick = onNavigateToInventory
        )
        LelloBigestFloatingActionButton(
            icon = LelloIcons.Graphic.AchievementsShop.imageVector,
            contentDescription = "Loja",
            onClick = onNavigateToStore
        )
    }
}

// region: Previes

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun AchievementScreenPreview_LightMode() {
    LelloTheme {
        AchievementScreenContent(
            vitality = 50,
            money = 250,
            isMuted = false,
            onMuteToggle = {},
            onNavigateToStore = {},
            onNavigateToInventory = {},
            onBack = {}
        )
    }
}

// endregion: Previes