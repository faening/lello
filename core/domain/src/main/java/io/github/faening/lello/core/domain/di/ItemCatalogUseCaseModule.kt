package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.ItemCategoryRepository
import io.github.faening.lello.core.domain.usecase.item.catalog.GetAllItemCatalogUseCase
import io.github.faening.lello.core.domain.usecase.item.catalog.GetItemCatalogByIdUseCase
import io.github.faening.lello.core.domain.usecase.item.catalog.GetItemCatalogsByTypeUseCase
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType

@Module
@InstallIn(SingletonComponent::class)
object ItemCatalogUseCaseModule {

    @Provides
    fun provideGetAllItemCatalogUseCase(
        repository: ItemCategoryRepository<ItemCatalog, ItemType>
    ) = GetAllItemCatalogUseCase(repository)

    @Provides
    fun provideGetItemCatalogByIdUseCase(
        repository: ItemCategoryRepository<ItemCatalog, ItemType>
    ) = GetItemCatalogByIdUseCase(repository)

    @Provides
    fun provideGetItemCatalogsByTypeUseCase(
        repository: ItemCategoryRepository<ItemCatalog, ItemType>
    ) = GetItemCatalogsByTypeUseCase(repository)
}
