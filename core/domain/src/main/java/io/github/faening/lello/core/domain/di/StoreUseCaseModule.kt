package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.domain.repository.PurchaseHistoryRepository
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.store.BuyItemUseCase
import io.github.faening.lello.core.domain.usecase.store.GetInventoryItemsUseCase
import io.github.faening.lello.core.domain.usecase.store.GetPurchaseHistoryUseCase
import io.github.faening.lello.core.domain.usecase.store.GetStoreItemsUseCase
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.PurchaseHistory

@Module
@InstallIn(SingletonComponent::class)
object StoreUseCaseModule {

    @Provides
    fun provideBuyItemUseCase(
        itemRepository: ItemRepository<ItemCatalog>,
        inventoryRepository: InventoryRepository<InventoryItem>,
        purchaseHistoryRepository: PurchaseHistoryRepository<PurchaseHistory>,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase
    ) = BuyItemUseCase(
        itemRepository,
        inventoryRepository,
        purchaseHistoryRepository,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase
    )

    @Provides
    fun provideGetInventoryItemsUseCase(
        repository: InventoryRepository<InventoryItem>
    ) = GetInventoryItemsUseCase(repository)

    @Provides
    fun provideGetPurchaseHistoryUseCase(
        repository: PurchaseHistoryRepository<PurchaseHistory>
    ) = GetPurchaseHistoryUseCase(repository)

    @Provides
    fun provideGetStoreItemsUseCase(
        itemRepository: ItemRepository<ItemCatalog>,
        inventoryRepository: InventoryRepository<InventoryItem>
    ) = GetStoreItemsUseCase(itemRepository, inventoryRepository)
}
