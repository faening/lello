package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.store.ItemInventoryEntity
import io.github.faening.lello.core.domain.repository.ItemInventoryDaoContract

@Suppress("unused")
@Dao
interface ItemInventoryDao : ItemInventoryDaoContract<ItemInventoryEntity> {

    @Transaction
    @Query("SELECT * FROM item_inventory")
    override suspend fun getAllItems(): List<ItemInventoryEntity>

    @Transaction
    @Query("SELECT * FROM item_inventory WHERE item_inventory_id = :id")
    override suspend fun getById(id: Long): ItemInventoryEntity?

    @Transaction
    @Query("SELECT * FROM item_inventory WHERE item_catalog_id = :itemId")
    override suspend fun getByItemId(itemId: Long): ItemInventoryEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun insert(item: ItemInventoryEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(item: ItemInventoryEntity)
}