package io.github.faening.lello.core.domain.usecase.item.inventory

import io.github.faening.lello.core.domain.repository.ItemInventoryRepository
import io.github.faening.lello.core.model.store.ItemInventory
import javax.inject.Inject

class UpdateItemInventoryUseCase @Inject constructor(
    private val repository: ItemInventoryRepository<ItemInventory>
) {
    suspend operator fun invoke(item: ItemInventory) {
        repository.update(item)
    }
}