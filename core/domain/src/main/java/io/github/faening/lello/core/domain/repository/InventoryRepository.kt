package io.github.faening.lello.core.domain.repository

interface InventoryRepository<T> {
    suspend fun getInventory(): List<T>
    suspend fun getInventoryItem(itemId: String): T?
    suspend fun updateInventoryItem(itemId: String, delta: Int): T
}
