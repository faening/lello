package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.ItemInventoryRepository
import io.github.faening.lello.core.domain.usecase.item.inventory.GetAllItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.GetItemInventoryByIdUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.GetItemInventoryByItemIdUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.InsertItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.UpdateItemInventoryUseCase
import io.github.faening.lello.core.model.store.ItemInventory

@Module
@InstallIn(SingletonComponent::class)
object ItemInventoryUseCaseModule {

    @Provides
    fun provideGetAllItemInventoryUseCase(
        repository: ItemInventoryRepository<ItemInventory>
    ) = GetAllItemInventoryUseCase(repository)

    @Provides
    fun provideGetItemInventoryByIdUseCase(
        repository: ItemInventoryRepository<ItemInventory>
    ) = GetItemInventoryByIdUseCase(repository)

    @Provides
    fun provideGetItemInventoryByItemIdUseCase(
        repository: ItemInventoryRepository<ItemInventory>
    ) = GetItemInventoryByItemIdUseCase(repository)

    @Provides
    fun provideInsertItemInventoryUseCase(
        repository: ItemInventoryRepository<ItemInventory>
    ) = InsertItemInventoryUseCase(repository)

    @Provides
    fun provideUpdateItemInventoryUseCase(
        repository: ItemInventoryRepository<ItemInventory>
    ) = UpdateItemInventoryUseCase(repository)
}