package io.github.faening.lello.core.model.store

data class PurchaseHistory(
    val id: Long = 0L,
    val itemId: String,
    val amount: Int,
    val purchasedAt: Long,
    val price: Int
)
