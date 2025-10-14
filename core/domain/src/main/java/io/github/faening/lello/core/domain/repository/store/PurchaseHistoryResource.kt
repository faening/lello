package io.github.faening.lello.core.domain.repository.store

interface PurchaseHistoryResource<T> {
    suspend fun addPurchase(itemId: String, amount: Int, price: Int): T
    suspend fun getPurchaseHistory(from: Long, to: Long): List<T>
    suspend fun getPurchaseHistory(): List<T>
}
