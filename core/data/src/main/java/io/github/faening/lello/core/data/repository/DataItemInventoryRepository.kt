package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.ItemInventoryDao
import io.github.faening.lello.core.database.model.item.toEntity
import io.github.faening.lello.core.database.model.item.toModel
import io.github.faening.lello.core.domain.repository.ItemInventoryRepository
import io.github.faening.lello.core.model.store.ItemInventory
import javax.inject.Inject

class DataItemInventoryRepository @Inject constructor(
    private val dao: ItemInventoryDao
) : ItemInventoryRepository<ItemInventory> {

    override suspend fun getAllItems(): List<ItemInventory> {
        return dao.getAllItems().map { it.toModel() }
    }

    override suspend fun getById(id: Long): ItemInventory? {
        return dao.getById(id)?.toModel()
    }

    override suspend fun getByItemId(itemId: Long): ItemInventory? {
        return dao.getByItemId(itemId)?.toModel()
    }

    override suspend fun insert(item: ItemInventory): Long {
        return dao.insert(item.toEntity())
    }

    override suspend fun update(item: ItemInventory) {
        dao.update(item.toEntity())
    }
}