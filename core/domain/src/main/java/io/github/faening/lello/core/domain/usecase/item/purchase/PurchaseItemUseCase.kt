package io.github.faening.lello.core.domain.usecase.item.purchase

import io.github.faening.lello.core.domain.service.MascotVitalityManager
import io.github.faening.lello.core.domain.usecase.item.inventory.GetItemInventoryByItemIdUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.InsertItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemInventory
import io.github.faening.lello.core.model.store.ItemType
import java.time.Instant
import javax.inject.Inject

// Exceções customizadas para tratar erros na UI
class NotEnoughCurrencyException : Exception("Você não tem moedas suficientes.")
class ItemAlreadyOwnedException : Exception("Você já possui este item.")

class PurchaseItemUseCase @Inject constructor(
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val getItemInventoryByItemIdUseCase: GetItemInventoryByItemIdUseCase,
    private val insertItemInventoryUseCase: InsertItemInventoryUseCase,
    private val mascotVitalityManager: MascotVitalityManager
    // Opcional: private val savePurchaseHistoryUseCase: SavePurchaseHistoryUseCase
) {
    suspend operator fun invoke(item: ItemCatalog) {
        val currentBalance = getRewardBalanceUseCase.invoke()
        if (currentBalance != null) {
            // Verifica se há saldo suficiente
            if (currentBalance.totalCoins < item.price) {
                throw NotEnoughCurrencyException()
            }

            val newBalance = currentBalance.copy(
                totalCoins = currentBalance.totalCoins - item.price
            )

            if (item.type == ItemType.CONSUMABLE) {
                mascotVitalityManager.feedMascot(item.vitalityGain ?: 0)
            } else {
                // Verifica se o item já é adquirido
                val existingItem = getItemInventoryByItemIdUseCase(item.id)
                if (existingItem != null) {
                    throw ItemAlreadyOwnedException()
                }

                // Adiciona ao inventário
                val inventoryItem = ItemInventory(
                    id = 0L,
                    itemCatalogId = item.id,
                    acquisitionDate = Instant.now().toEpochMilli(),
                    isEquipped = false
                )
                insertItemInventoryUseCase(inventoryItem)

                // TODO: (Opcional) Salva no histórico de compras
            }

            saveOrUpdateRewardBalanceUseCase(newBalance)
        }
    }
}