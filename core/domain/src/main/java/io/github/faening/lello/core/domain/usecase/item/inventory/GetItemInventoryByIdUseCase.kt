package io.github.faening.lello.core.domain.usecase.item.inventory

import io.github.faening.lello.core.domain.repository.ItemInventoryRepository
import io.github.faening.lello.core.model.store.ItemInventory
import javax.inject.Inject

class GetItemInventoryByIdUseCase @Inject constructor(
    private val repository: ItemInventoryRepository<ItemInventory>
) {
    suspend operator fun invoke(id: Long): ItemInventory? {
        return repository.getById(id)
    }
}