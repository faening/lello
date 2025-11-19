package io.github.faening.lello.core.model.store

data class ItemCatalog(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val imageResourceName: String,
    val type: ItemType,
    val vitalityGain: Int?,
    val isActive: Boolean
)