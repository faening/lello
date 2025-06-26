package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.store.InventoryResource
import io.github.faening.lello.core.domain.repository.store.ItemResource
import io.github.faening.lello.core.model.store.Item
import io.github.faening.lello.core.model.store.ItemType
import javax.inject.Inject

class GetStoreItemsUseCase @Inject constructor(
    private val itemResource: ItemResource,
    private val inventoryResource: InventoryResource
) {
    suspend operator fun invoke(): List<Item> {
        val allItems = itemResource.getAllItems()
        val ownedIds = inventoryResource.getInventory().map { it.itemId }.toSet()
        return allItems.filter { it.type == ItemType.FOOD || it.id !in ownedIds }
    }
}
