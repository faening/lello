package io.github.faening.lello.core.model.store

data class Item(
    val id: String,
    val type: ItemType,
    val name: String,
    val description: String,
    val price: Int,
    val iconRes: String
)
