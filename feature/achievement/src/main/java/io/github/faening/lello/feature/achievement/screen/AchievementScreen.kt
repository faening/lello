package io.github.faening.lello.feature.achievement.screen

import android.content.res.Configuration
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import io.github.faening.lello.core.audio.AudioManager
import io.github.faening.lello.core.audio.AudioTrack
import io.github.faening.lello.core.designsystem.component.appbar.LelloAchievementTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.button.LelloBigestFloatingActionButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemInventory
import io.github.faening.lello.feature.achievement.AchievementViewModel
import kotlinx.coroutines.delay

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
    val exoPlayer = viewModel.exoPlayer
    var isExiting by remember { mutableStateOf(false) }

    // Prepara o vídeo apenas uma vez
    LaunchedEffect(Unit) {
        viewModel.prepareVideo(context)
    }

    // Gerencia o ciclo de vida do vídeo
    DisposableEffect(Unit) {
        viewModel.resumeVideo()
        onDispose {
            viewModel.pauseVideo()
        }
    }

    // Inicia a animação de saída ao voltar
    LaunchedEffect(isExiting) {
        if (isExiting) {
            delay(500)
            onBack()
        }
    }

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
        equippedItems = uiState.equippedItems,
        isVideoReady = uiState.isVideoReady,
        isMuted = isMuted,
        isExiting = isExiting,
        onMuteToggle = { isMuted = !isMuted },
        onNavigateToStore = onNavigateToStore,
        onNavigateToInventory = onNavigateToInventory,
        exoPlayer = exoPlayer,
        onBack = { isExiting = true }
    )
}

@Composable
private fun AchievementScreenContent(
    vitality: Int,
    money: Int,
    equippedItems: List<Pair<ItemInventory, ItemCatalog>>,
    isVideoReady: Boolean,
    isMuted: Boolean,
    isExiting: Boolean,
    onMuteToggle: () -> Unit,
    onNavigateToStore: () -> Unit,
    onNavigateToInventory: () -> Unit,
    exoPlayer: ExoPlayer?,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AchievementScreenBackground(
            exoPlayer = exoPlayer,
            isExiting = isExiting,
        )

        EquippedItemsLayer(
            equippedItems = equippedItems,
            isVideoReady = isVideoReady
        )

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

@OptIn(UnstableApi::class)
@Composable
private fun AchievementScreenBackground(
    exoPlayer: ExoPlayer?,
    isExiting: Boolean = false,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (exoPlayer != null) {
            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                        layoutParams = FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                        )
                    }
                },
                update = { view ->
                    view.alpha = if (isExiting) 0f else 1f
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
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
private fun EquippedItemsLayer(
    equippedItems: List<Pair<ItemInventory, ItemCatalog>>,
    isVideoReady: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val alpha by animateFloatAsState(
        targetValue = if (isVideoReady) 0.5f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "equipped_items_alpha"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer { this.alpha = alpha }
    ) {
        equippedItems
            .sortedBy { it.second.zIndex }
            .forEach { (_, catalogItem) ->
                catalogItem.backgroundImageResourceName.let { resourceName ->
                    val imageResId = remember(resourceName) {
                        context.resources.getIdentifier(
                            resourceName,
                            "drawable",
                            context.packageName
                        )
                    }

                    if (imageResId != 0) {
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = catalogItem.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .offset(x = catalogItem.offsetX.dp, y = -catalogItem.offsetY.dp)
                                .fillMaxSize()
                        )
                    }
                }
            }
    }
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
            equippedItems = emptyList(),
            isVideoReady = true,
            isMuted = false,
            isExiting = false,
            onMuteToggle = {},
            onNavigateToStore = {},
            onNavigateToInventory = {},
            exoPlayer = null,
            onBack = {}
        )
    }
}

// endregion: Previes