package io.github.faening.lello.core.domain.repository.store

import io.github.faening.lello.core.model.store.PurchaseHistory

interface PurchaseHistoryResource {
    suspend fun addPurchase(itemId: String, amount: Int, price: Int): PurchaseHistory
    suspend fun getPurchaseHistory(from: Long, to: Long): List<PurchaseHistory>
    suspend fun getPurchaseHistory(): List<PurchaseHistory>
}
