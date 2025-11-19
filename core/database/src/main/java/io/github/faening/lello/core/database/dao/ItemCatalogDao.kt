package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.faening.lello.core.database.model.item.ItemCatalogEntity
import io.github.faening.lello.core.domain.repository.ItemCategoryDaoContract

@Dao
interface ItemCatalogDao : ItemCategoryDaoContract<ItemCatalogEntity> {
    @Query("SELECT * FROM item_catalog")
    override suspend fun getAllItems(): List<ItemCatalogEntity>

    @Query("SELECT * FROM item_catalog WHERE item_catalog_id = :id LIMIT 1")
    override suspend fun getItemById(id: Long): ItemCatalogEntity?
}
