package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.service.MascotVitalityManager
import io.github.faening.lello.core.domain.usecase.item.catalog.GetItemCatalogByIdUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.GetAllItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.GetItemInventoryByItemIdUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.InsertItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.UpdateItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.item.purchase.EquipItemUseCase
import io.github.faening.lello.core.domain.usecase.item.purchase.PurchaseItemUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase

@Module
@InstallIn(SingletonComponent::class)
object ItemPurchaseUseCaseModule {

    @Provides
    fun provideEquipItemUseCase(
        getItemCatalogByIdUseCase: GetItemCatalogByIdUseCase,
        getAllItemInventoryUseCase: GetAllItemInventoryUseCase,
        updateItemInventoryUseCase: UpdateItemInventoryUseCase
    ) = EquipItemUseCase(
        getItemCatalogByIdUseCase,
        getAllItemInventoryUseCase,
        updateItemInventoryUseCase
    )

    @Provides
    fun providePurchaseItemUseCase(
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getItemInventoryByItemIdUseCase: GetItemInventoryByItemIdUseCase,
        insertItemInventoryUseCase: InsertItemInventoryUseCase,
        mascotVitalityManager: MascotVitalityManager
    ) = PurchaseItemUseCase(
        getRewardBalanceUseCase,
        saveOrUpdateRewardBalanceUseCase,
        getItemInventoryByItemIdUseCase,
        insertItemInventoryUseCase,
        mascotVitalityManager
    )
}