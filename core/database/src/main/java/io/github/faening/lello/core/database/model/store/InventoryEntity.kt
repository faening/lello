package io.github.faening.lello.core.database.model.store

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.store.InventoryItem

@Entity(tableName = "inventory")
data class InventoryEntity(
    @PrimaryKey val itemId: String,
    val quantity: Int,
    val acquiredAt: Long
)

fun InventoryEntity.toModel() = InventoryItem(
    itemId = itemId,
    quantity = quantity,
    acquiredAt = acquiredAt
)

fun InventoryItem.toEntity() = InventoryEntity(
    itemId = itemId,
    quantity = quantity,
    acquiredAt = acquiredAt
)
