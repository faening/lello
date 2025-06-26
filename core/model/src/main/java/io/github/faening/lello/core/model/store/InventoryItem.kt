package io.github.faening.lello.core.model.store

data class InventoryItem(
    val itemId: String,
    val quantity: Int,
    val acquiredAt: Long
)
