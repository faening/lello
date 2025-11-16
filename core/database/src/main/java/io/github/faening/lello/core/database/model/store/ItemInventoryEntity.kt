package io.github.faening.lello.core.database.model.store

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.store.ItemInventory

@Entity(
    tableName = "item_inventory",
    foreignKeys = [
        ForeignKey(
            entity = ItemCatalogEntity::class,
            parentColumns = ["item_catalog_id"],
            childColumns = ["item_catalog_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            value = ["item_catalog_id"],
            name = "index_item_inventory_on_item_catalog_id",
            unique = true
        )
    ]
)
data class ItemInventoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_inventory_id")
    val inventoryId: Long = 0L,

    @ColumnInfo(name = "item_catalog_id", index = true)
    val itemCatalogId: Long,

    @ColumnInfo(name = "acquisition_date")
    val acquisitionDate: Long,

    @ColumnInfo(name = "is_equipped")
    val isEquipped: Boolean
)

fun ItemInventoryEntity.toModel() = ItemInventory(
    id = inventoryId,
    itemCatalogId = itemCatalogId,
    acquisitionDate = acquisitionDate,
    isEquipped = isEquipped
)

fun ItemInventory.toEntity() = ItemInventoryEntity(
    inventoryId = id,
    itemCatalogId = itemCatalogId,
    acquisitionDate = acquisitionDate,
    isEquipped = isEquipped
)