package io.github.faening.lello.core.domain.repository.store

import io.github.faening.lello.core.model.store.InventoryItem

interface InventoryResource {
    suspend fun getInventory(): List<InventoryItem>
    suspend fun getInventoryItem(itemId: String): InventoryItem?
    suspend fun updateInventoryItem(itemId: String, delta: Int): InventoryItem
}
