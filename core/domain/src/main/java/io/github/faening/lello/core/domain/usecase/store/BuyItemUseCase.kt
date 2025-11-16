package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.domain.repository.PurchaseHistoryRepository
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.PurchaseHistory
import javax.inject.Inject

class BuyItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository<ItemCatalog>,
    private val inventoryRepository: InventoryRepository<InventoryItem>,
    private val purchaseHistoryRepository: PurchaseHistoryRepository<PurchaseHistory>,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase
) {
    suspend operator fun invoke(itemId: String, amount: Int): PurchaseHistory {
        val item = itemRepository.getItemById(itemId) ?: throw IllegalArgumentException("Item not found")
        val balance = getRewardBalanceUseCase.invoke() ?: RewardBalance()
        val cost = item.price * amount
        require(balance.totalCoins >= cost) { "Insufficient coins" }

        saveOrUpdateRewardBalanceUseCase.invoke(balance.copy(totalCoins = balance.totalCoins - cost))
        inventoryRepository.updateInventoryItem(itemId, amount)
        return purchaseHistoryRepository.addPurchase(itemId, amount, item.price)
    }
}
