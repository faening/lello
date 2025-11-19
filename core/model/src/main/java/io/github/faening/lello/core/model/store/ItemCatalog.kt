package io.github.faening.lello.core.model.store

data class ItemCatalog(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val imageResourceName: String,
    val backgroundImageResourceName: String,
    val type: ItemType,
    val vitalityGain: Int?,
    val isActive: Boolean,

    // Propriedades adicionais para posicionamento na tela
    val offsetX: Int = 0,  // Offset X em dp
    val offsetY: Int = 0,  // Offset Y em dp
    val zIndex: Int = 0    // Ordem de renderização (maior = mais na frente)
)