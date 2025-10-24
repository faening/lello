package io.github.faening.lello.core.data.repository.store

import io.github.faening.lello.core.database.dao.InventoryDao
import io.github.faening.lello.core.database.model.store.toModel
import io.github.faening.lello.core.domain.repository.InventoryResource
import io.github.faening.lello.core.model.store.InventoryItem
import javax.inject.Inject

class InventoryRepository @Inject constructor(
    private val dao: InventoryDao
) : InventoryResource<InventoryItem> {
    override suspend fun getInventory(): List<InventoryItem> {
        return dao.getInventory().map { it.toModel() }
    }

    override suspend fun getInventoryItem(itemId: String): InventoryItem? {
        return dao.getInventoryItem(itemId)?.toModel()
    }

    override suspend fun updateInventoryItem(itemId: String, delta: Int): InventoryItem {
        return dao.updateInventoryItem(itemId, delta).toModel()
    }
}
