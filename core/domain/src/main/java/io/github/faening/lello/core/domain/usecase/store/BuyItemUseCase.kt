package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemResource
import io.github.faening.lello.core.domain.repository.PurchaseHistoryResource
import io.github.faening.lello.core.domain.usecase.reward.RewardBalanceUseCase
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.Item
import io.github.faening.lello.core.model.store.PurchaseHistory
import javax.inject.Inject

class BuyItemUseCase @Inject constructor(
    private val itemResource: ItemResource<Item>,
    private val inventoryRepository: InventoryRepository<InventoryItem>,
    private val purchaseHistoryResource: PurchaseHistoryResource<PurchaseHistory>,
    private val rewardBalanceUseCase: RewardBalanceUseCase
) {
    suspend operator fun invoke(itemId: String, amount: Int): PurchaseHistory {
        val item = itemResource.getItemById(itemId) ?: throw IllegalArgumentException("Item not found")
        val balance = rewardBalanceUseCase.getBalance() ?: RewardBalance()
        val cost = item.price * amount
        require(balance.totalCoins >= cost) { "Insufficient coins" }

        rewardBalanceUseCase.insertOrUpdate(balance.copy(totalCoins = balance.totalCoins - cost))
        inventoryRepository.updateInventoryItem(itemId, amount)
        return purchaseHistoryResource.addPurchase(itemId, amount, item.price)
    }
}
