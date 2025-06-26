package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.faening.lello.core.database.model.store.ItemCatalogEntity
import io.github.faening.lello.core.domain.repository.store.ItemResource

@Dao
interface ItemCatalogDao : ItemResource<ItemCatalogEntity> {
    @Query("SELECT * FROM item_catalog")
    override suspend fun getAllItems(): List<ItemCatalogEntity>

    @Query("SELECT * FROM item_catalog WHERE id = :id LIMIT 1")
    override suspend fun getItemById(id: String): ItemCatalogEntity?
}
