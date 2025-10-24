package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.Item
import io.github.faening.lello.core.model.store.ItemType
import javax.inject.Inject

class GetStoreItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository<Item>,
    private val inventoryRepository: InventoryRepository<InventoryItem>
) {
    suspend operator fun invoke(): List<Item> {
        val allItems = itemRepository.getAllItems()
        val ownedIds = inventoryRepository.getInventory().map { it.itemId }.toSet()
        return allItems.filter { it.type == ItemType.FOOD || it.id !in ownedIds }
    }
}
