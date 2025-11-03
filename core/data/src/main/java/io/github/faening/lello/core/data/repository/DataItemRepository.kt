package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.ItemCatalogDao
import io.github.faening.lello.core.database.model.store.toModel
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.model.store.Item
import javax.inject.Inject

class DataItemRepository @Inject constructor(
    private val dao: ItemCatalogDao
) : ItemRepository<Item> {

    override suspend fun getAllItems(): List<Item> {
        return dao.getAllItems().map { it.toModel() }
    }

    override suspend fun getItemById(id: String): Item? {
        return dao.getItemById(id)?.toModel()
    }
}