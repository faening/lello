package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.model.store.InventoryItem
import javax.inject.Inject

class GetInventoryItemsUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository<InventoryItem>
) {
    suspend operator fun invoke(): List<InventoryItem> {
        return inventoryRepository.getInventory().filter { it.quantity > 0 }
    }
}
