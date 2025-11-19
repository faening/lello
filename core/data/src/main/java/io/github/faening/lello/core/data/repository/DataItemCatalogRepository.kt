package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.ItemCatalogDao
import io.github.faening.lello.core.database.model.item.toModel
import io.github.faening.lello.core.domain.repository.ItemCategoryRepository
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType
import javax.inject.Inject

class DataItemCatalogRepository @Inject constructor(
    private val dao: ItemCatalogDao
) : ItemCategoryRepository<ItemCatalog, ItemType> {

    override suspend fun getAllItems(): List<ItemCatalog> {
        return dao.getAllItems().map { it.toModel() }
    }

    override suspend fun getItemById(id: Long): ItemCatalog? {
        return dao.getItemById(id)?.toModel()
    }

    override suspend fun getItemsByType(type: ItemType): List<ItemCatalog> {
        return dao.getAllItems().filter { it.type == type }.map { it.toModel() }
    }
}