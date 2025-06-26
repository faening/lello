package io.github.faening.lello.core.database.model.store

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.store.Item
import io.github.faening.lello.core.model.store.ItemType

@Entity(tableName = "item_catalog")
data class ItemCatalogEntity(
    @PrimaryKey val id: String,
    val type: String,
    val name: String,
    val description: String,
    val price: Int,
    val iconRes: String
)

fun ItemCatalogEntity.toModel() = Item(
    id = id,
    type = ItemType.valueOf(type),
    name = name,
    description = description,
    price = price,
    iconRes = iconRes
)

fun Item.toEntity() = ItemCatalogEntity(
    id = id,
    type = type.name,
    name = name,
    description = description,
    price = price,
    iconRes = iconRes
)
