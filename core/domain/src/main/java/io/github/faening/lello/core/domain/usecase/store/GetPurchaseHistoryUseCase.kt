package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.PurchaseHistoryRepository
import io.github.faening.lello.core.model.store.PurchaseHistory
import javax.inject.Inject

class GetPurchaseHistoryUseCase @Inject constructor(
    private val purchaseHistoryRepository: PurchaseHistoryRepository<PurchaseHistory>
) {
    suspend operator fun invoke(): List<PurchaseHistory> {
        return purchaseHistoryRepository.getPurchaseHistory()
    }
}
