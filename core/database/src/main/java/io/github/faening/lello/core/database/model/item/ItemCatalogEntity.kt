package io.github.faening.lello.core.database.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType

@Entity(
    tableName = "item_catalog",
    indices = [Index(value = ["name"], unique = true)]
)
data class ItemCatalogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_catalog_id")
    val itemCatalogId: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Int,

    @ColumnInfo(name = "image_resource_name")
    val imageResourceName: String,

    @ColumnInfo(name = "type")
    val type: ItemType,

    @ColumnInfo(name = "vitality_gain", defaultValue = "NULL")
    val vitalityGain: Int?,

    @ColumnInfo(name = "is_active")
    val isActive: Boolean
)

fun ItemCatalogEntity.toModel(): ItemCatalog = ItemCatalog(
    id = itemCatalogId,
    name = name,
    description = description,
    price = price,
    imageResourceName = imageResourceName,
    type = type,
    vitalityGain = vitalityGain,
    isActive = isActive
)

fun ItemCatalog.toEntity(): ItemCatalogEntity = ItemCatalogEntity(
    itemCatalogId = id,
    name = name,
    description = description,
    price = price,
    imageResourceName = imageResourceName,
    type = type,
    vitalityGain = vitalityGain,
    isActive = isActive
)