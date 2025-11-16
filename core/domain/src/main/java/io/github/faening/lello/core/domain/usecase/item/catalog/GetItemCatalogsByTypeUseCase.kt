package io.github.faening.lello.core.domain.usecase.item.catalog

import io.github.faening.lello.core.domain.repository.ItemCategoryRepository
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType
import javax.inject.Inject

class GetItemCatalogsByTypeUseCase @Inject constructor(
    private val repository: ItemCategoryRepository<ItemCatalog, ItemType>
) {
    suspend operator fun invoke(type: ItemType): List<ItemCatalog> {
        return repository.getItemsByType(type)
    }
}