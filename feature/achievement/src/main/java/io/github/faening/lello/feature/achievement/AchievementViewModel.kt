package io.github.faening.lello.feature.achievement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.item.catalog.GetAllItemCatalogUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.GetAllItemInventoryUseCase
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
    private val purchaseItemUseCase: PurchaseItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AchievementUiState())
    val uiState: StateFlow<AchievementUiState> = _uiState.asStateFlow()

    init {
        loadStoreData()
    }

    /**
     * Carrega todos os dados necessários para a tela da loja e os combina.
     */
    private fun loadStoreData() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            // Carrega dinheiro
            launch {
                val money = getRewardBalanceUseCase.invoke()?.totalCoins ?: 0
                _uiState.update { it.copy(money = money) }
            }

            // Carrega vitalidade
            launch {
                val vitality = getMascotStatusUseCase.invoke().vitality
                _uiState.update { it.copy(vitality = vitality) }
            }

            // Lógica principal: Carregar catálogo, carregar inventário e filtrar
            val allItems = getAllItemCatalogUseCase.invoke() // Lista completa
            val inventoryItems = getAllItemInventoryUseCase.invoke() // Itens possuídos

            val ownedItemIds = inventoryItems
                .map { it.itemCatalogId }
                .toSet()

            // Filtra a lista para a LOJA
            val storeItems = allItems.filter { item ->
                item.type == ItemType.CONSUMABLE || item.id !in ownedItemIds
            }

            // Atualiza o state com TODAS as listas
            _uiState.update {
                it.copy(
                    allCatalogItems = allItems, // <-- AQUI! Passa a lista completa
                    storeItems = storeItems,      // Passa a lista filtrada
                    inventoryItems = inventoryItems, // Passa o inventário
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

    fun onErrorMessageShown() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun onPurchaseSuccessMessageShown() {
        _uiState.update { it.copy(purchaseSuccessMessage = null) }
    }

    fun onShowBottomSheet(item: ItemCatalog) {
        _uiState.update { it.copy(selectedItem = item) }
    }

    fun onDismissBottomSheet() {
        _uiState.update { it.copy(selectedItem = null) }
    }
}

data class AchievementUiState(
    val vitality: Int = 0,
    val money: Int = 0,

    // Lista COMPLETA do catálogo (para o Inventário usar)
    val allCatalogItems: List<ItemCatalog> = emptyList(),

    // Lista FILTRADA (para a Loja usar)
    val storeItems: List<ItemCatalog> = emptyList(),

    // Itens que o usuário possui
    val inventoryItems: List<ItemInventory> = emptyList(),

    // Estados para a compra
    val isLoading: Boolean = false,
    val selectedItem: ItemCatalog? = null,
    val errorMessage: String? = null,
    val purchaseSuccessMessage: String? = null
)