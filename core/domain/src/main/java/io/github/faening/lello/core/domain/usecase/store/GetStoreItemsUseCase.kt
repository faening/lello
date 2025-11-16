package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType
import javax.inject.Inject

class GetStoreItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository<ItemCatalog>,
    private val inventoryRepository: InventoryRepository<InventoryItem>
) {
    suspend operator fun invoke(): List<ItemCatalog> {
        val allItems = itemRepository.getAllItems()
        val ownedIds = inventoryRepository.getInventory().map { it.itemId }.toSet()
        return allItems.filter { it.type == ItemType.CONSUMABLE || it.id.toString() !in ownedIds }
    }
}
