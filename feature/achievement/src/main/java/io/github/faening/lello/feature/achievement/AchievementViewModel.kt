package io.github.faening.lello.feature.achievement

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.designsystem.media.LelloMedia
import io.github.faening.lello.core.domain.usecase.item.catalog.GetAllItemCatalogUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.GetAllItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.item.purchase.EquipItemUseCase
import io.github.faening.lello.core.domain.usecase.item.purchase.PurchaseItemUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemInventory
import io.github.faening.lello.core.model.store.ItemType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val getMascotStatusUseCase: GetMascotStatusUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase,
    private val getAllItemCatalogUseCase: GetAllItemCatalogUseCase,
    private val getAllItemInventoryUseCase: GetAllItemInventoryUseCase,
    private val equipItemUseCase: EquipItemUseCase,
    private val purchaseItemUseCase: PurchaseItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AchievementUiState())
    val uiState: StateFlow<AchievementUiState> = _uiState.asStateFlow()

    private var _exoPlayer: ExoPlayer? = null
    val exoPlayer: ExoPlayer? get() = _exoPlayer

    private var isVideoPrepared = false

    init {
        loadStoreData()
    }

    /**
     * Carrega todos os dados necessários para a tela da loja e os combina.
     */
    private fun loadStoreData() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            launch {
                val money = getRewardBalanceUseCase.invoke()?.totalCoins ?: 0
                _uiState.update { it.copy(money = money) }
            }
            launch {
                val vitality = getMascotStatusUseCase.invoke().vitality
                _uiState.update { it.copy(vitality = vitality) }
            }

            val allItems = getAllItemCatalogUseCase.invoke()
            val inventoryItems = getAllItemInventoryUseCase.invoke()
            val ownedItemIds = inventoryItems.map { it.itemCatalogId }.toSet()

            val storeItems = allItems.filter { item ->
                item.type == ItemType.CONSUMABLE || item.id !in ownedItemIds
            }

            val equippedItems = inventoryItems
                .filter { it.isEquipped }
                .mapNotNull { invItem ->
                    allItems.find { it.id == invItem.itemCatalogId }?.let { catalog ->
                        invItem to catalog
                    }
                }

            _uiState.update {
                it.copy(
                    allCatalogItems = allItems,
                    storeItems = storeItems,
                    inventoryItems = inventoryItems,
                    equippedItems = equippedItems,
                    isLoading = false
                )
            }
        }
    }

    fun onPurchaseItem(item: ItemCatalog) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                purchaseItemUseCase(item)
                _uiState.update {
                    it.copy(isLoading = false, purchaseSuccessMessage = "Item comprado com sucesso!")
                }

                loadStoreData()

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }

    /**
     * Chamado quando o usuário clica em um item no inventário.
     */
    fun onShowInventoryItemSheet(item: ItemInventory) {
        _uiState.update { it.copy(selectedInventoryItem = item) }
    }

    /**
     * Fecha o BottomSheet do inventário.
     */
    fun onDismissInventoryItemSheet() {
        _uiState.update { it.copy(selectedInventoryItem = null) }
    }

    /**
     * Chamado pelo botão "Equipar" do BottomSheet.
     */
    fun onEquipItem() {
        viewModelScope.launch {
            val itemToEquip = _uiState.value.selectedInventoryItem ?: return@launch

            _uiState.update { it.copy(isLoading = true) }
            try {
                equipItemUseCase(itemToEquip)
                _uiState.update { it.copy(isLoading = false) }
                loadStoreData() // Recarrega para mostrar o item equipado
                onDismissInventoryItemSheet() // Fecha o BottomSheet

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }

    fun onErrorMessageShown() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun onPurchaseSuccessMessageShown() {
        _uiState.update { it.copy(purchaseSuccessMessage = null) }
    }

    // Renomeie 'onShowBottomSheet' -> 'onShowStoreItemSheet'
    fun onShowStoreItemSheet(item: ItemCatalog) {
        _uiState.update { it.copy(selectedStoreItem = item) } // ATUALIZADO
    }

    // Renomeie 'onDismissBottomSheet' -> 'onDismissStoreItemSheet'
    fun onDismissStoreItemSheet() {
        _uiState.update { it.copy(selectedStoreItem = null) } // ATUALIZADO
    }

    /**
     * Prepara o ExoPlayer com o vídeo correspondente ao humor atual.
     *
     * @param context Contexto necessário para a criação do ExoPlayer.
     */
    @OptIn(UnstableApi::class)
    fun prepareVideo(context: Context) {
        if (isVideoPrepared && _exoPlayer != null) {
            _exoPlayer?.play()
            return
        }

        val video = LelloMedia.Video.AchievementCapybara

        _exoPlayer = ExoPlayer.Builder(context)
            .setRenderersFactory(
                DefaultRenderersFactory(context)
                    .setEnableDecoderFallback(true)
            )
            .build()
            .apply {
                val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${video.resId}")
                setMediaItem(mediaItem)
                repeatMode = Player.REPEAT_MODE_ALL
                volume = 0f
                prepare()
                playWhenReady = true

                // Marca como preparado após o vídeo estar pronto
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        if (state == Player.STATE_READY) {
                            isVideoPrepared = true
                            _uiState.update { it.copy(isVideoReady = true) }
                        }
                    }
                })
            }
    }

    /**
     * Pausa o vídeo quando a tela não está visível.
     */
    fun pauseVideo() {
        _exoPlayer?.pause()
    }

    /**
     * Retoma a reprodução do vídeo quando a tela volta a ficar visível.
     */
    fun resumeVideo() {
        _exoPlayer?.play()
    }

    override fun onCleared() {
        super.onCleared()
        _exoPlayer?.release()
        _exoPlayer = null
        isVideoPrepared = false
    }
}

data class AchievementUiState(
    val vitality: Int = 0,
    val money: Int = 0,

    val allCatalogItems: List<ItemCatalog> = emptyList(),
    val storeItems: List<ItemCatalog> = emptyList(),
    val selectedStoreItem: ItemCatalog? = null,
    val inventoryItems: List<ItemInventory> = emptyList(),
    val equippedItems: List<Pair<ItemInventory, ItemCatalog>> = emptyList(),

    // Estados para a compra (Loja)
    val isLoading: Boolean = false,
    val isVideoReady: Boolean = false,
    val errorMessage: String? = null,
    val purchaseSuccessMessage: String? = null,

    // NOVO ESTADO: para o BottomSheet do Inventário
    val selectedInventoryItem: ItemInventory? = null
)