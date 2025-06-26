package io.github.faening.lello.core.domain.usecase.store

import io.github.faening.lello.core.domain.repository.store.InventoryResource
import io.github.faening.lello.core.model.store.InventoryItem
import javax.inject.Inject

class GetInventoryItemsUseCase @Inject constructor(
    private val inventoryResource: InventoryResource<InventoryItem>
) {
    suspend operator fun invoke(): List<InventoryItem> {
        return inventoryResource.getInventory().filter { it.quantity > 0 }
    }
}
