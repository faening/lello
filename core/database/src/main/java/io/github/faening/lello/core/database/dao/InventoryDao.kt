package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.store.InventoryEntity
import io.github.faening.lello.core.domain.repository.store.InventoryResource

@Dao
interface InventoryDao : InventoryResource<InventoryEntity> {
    @Query("SELECT * FROM inventory")
    override suspend fun getInventory(): List<InventoryEntity>

    @Query("SELECT * FROM inventory WHERE itemId = :itemId LIMIT 1")
    override suspend fun getInventoryItem(itemId: String): InventoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entity: InventoryEntity)

    override suspend fun updateInventoryItem(itemId: String, delta: Int): InventoryEntity {
        val current = getInventoryItem(itemId)
        val newQuantity = (current?.quantity ?: 0) + delta
        val entity = InventoryEntity(itemId, newQuantity, System.currentTimeMillis())
        insertOrUpdate(entity)
        return entity
    }
}
