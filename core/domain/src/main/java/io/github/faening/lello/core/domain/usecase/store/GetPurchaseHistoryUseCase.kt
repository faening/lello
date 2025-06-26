package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.store.PurchaseHistoryResource
import io.github.faening.lello.core.model.store.PurchaseHistory
import javax.inject.Inject

class GetPurchaseHistoryUseCase @Inject constructor(
    private val purchaseHistoryResource: PurchaseHistoryResource
) {
    suspend operator fun invoke(): List<PurchaseHistory> {
        return purchaseHistoryResource.getPurchaseHistory()
    }
}
