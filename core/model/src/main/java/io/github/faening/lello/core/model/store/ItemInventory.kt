package io.github.faening.lello.core.model.store

data class ItemInventory(
    val id: Long,
    val itemCatalogId: Long,
    val acquisitionDate: Long,
    val isEquipped: Boolean
)