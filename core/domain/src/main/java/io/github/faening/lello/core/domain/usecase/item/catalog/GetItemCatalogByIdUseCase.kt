package io.github.faening.lello.core.domain.usecase.item.catalog

import io.github.faening.lello.core.domain.repository.ItemCategoryRepository
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType
import javax.inject.Inject

class GetItemCatalogByIdUseCase @Inject constructor(
    private val repository: ItemCategoryRepository<ItemCatalog, ItemType>
) {
    suspend operator fun invoke(id: Long): ItemCatalog? {
        return repository.getItemById(id)
    }
}