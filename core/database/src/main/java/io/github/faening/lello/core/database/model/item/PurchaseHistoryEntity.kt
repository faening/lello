package io.github.faening.lello.core.database.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.store.PurchaseHistory

@Entity(tableName = "purchase_history")
data class PurchaseHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "item_id") val itemId: String,
    val amount: Int,
    @ColumnInfo(name = "purchased_at") val purchasedAt: Long,
    val price: Int
)

fun PurchaseHistoryEntity.toModel() = PurchaseHistory(
    id = id,
    itemId = itemId,
    amount = amount,
    purchasedAt = purchasedAt,
    price = price
)

fun PurchaseHistory.toEntity() = PurchaseHistoryEntity(
    id = id,
    itemId = itemId,
    amount = amount,
    purchasedAt = purchasedAt,
    price = price
)
