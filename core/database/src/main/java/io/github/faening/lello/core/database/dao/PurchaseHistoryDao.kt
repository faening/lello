package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.store.PurchaseHistoryEntity
import io.github.faening.lello.core.domain.repository.PurchaseHistoryRepository

@Dao
interface PurchaseHistoryDao : PurchaseHistoryRepository<PurchaseHistoryEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PurchaseHistoryEntity): Long

    override suspend fun addPurchase(itemId: String, amount: Int, price: Int): PurchaseHistoryEntity {
        val entity = PurchaseHistoryEntity(
            itemId = itemId,
            amount = amount,
            purchasedAt = System.currentTimeMillis(),
            price = price
        )
        val id = insert(entity)
        return entity.copy(id = id)
    }

    @Query("SELECT * FROM purchase_history WHERE purchased_at BETWEEN :from AND :to ORDER BY purchased_at DESC")
    override suspend fun getPurchaseHistory(from: Long, to: Long): List<PurchaseHistoryEntity>

    @Query("SELECT * FROM purchase_history ORDER BY purchased_at DESC")
    override suspend fun getPurchaseHistory(): List<PurchaseHistoryEntity>
}
