package io.github.faening.lello.core.domain.usecase.item.inventory

import io.github.faening.lello.core.domain.repository.ItemInventoryRepository
import io.github.faening.lello.core.model.store.ItemInventory
import javax.inject.Inject

class InsertItemInventoryUseCase @Inject constructor(
    private val repository: ItemInventoryRepository<ItemInventory>
) {
    suspend operator fun invoke(item: ItemInventory): Long {
        return repository.insert(item)
    }
}