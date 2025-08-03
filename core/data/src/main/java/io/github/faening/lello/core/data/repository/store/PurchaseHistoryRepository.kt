package io.github.faening.lello.core.data.repository.store

import io.github.faening.lello.core.database.dao.PurchaseHistoryDao
import io.github.faening.lello.core.database.model.store.toModel
import io.github.faening.lello.core.domain.repository.store.PurchaseHistoryResource
import io.github.faening.lello.core.model.store.PurchaseHistory
import javax.inject.Inject

class PurchaseHistoryRepository @Inject constructor(
    private val dao: PurchaseHistoryDao
) : PurchaseHistoryResource<PurchaseHistory> {
    override suspend fun addPurchase(itemId: String, amount: Int, price: Int): PurchaseHistory {
        return dao.addPurchase(itemId, amount, price).toModel()
    }

    override suspend fun getPurchaseHistory(from: Long, to: Long): List<PurchaseHistory> {
        return dao.getPurchaseHistory(from, to).map { it.toModel() }
    }

    override suspend fun getPurchaseHistory(): List<PurchaseHistory> {
        return dao.getPurchaseHistory().map { it.toModel() }
    }
}
