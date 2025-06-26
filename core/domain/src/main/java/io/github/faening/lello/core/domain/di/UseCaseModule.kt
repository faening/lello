package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.domain.usecase.options.JournalCategoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.mascot.UpdateMascotVitalityUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotVitalityHistoryUseCase
import io.github.faening.lello.core.domain.repository.store.ItemResource
import io.github.faening.lello.core.domain.repository.store.InventoryResource
import io.github.faening.lello.core.domain.repository.store.PurchaseHistoryResource
import io.github.faening.lello.core.domain.usecase.store.BuyItemUseCase
import io.github.faening.lello.core.domain.usecase.store.GetInventoryItemsUseCase
import io.github.faening.lello.core.domain.usecase.store.GetPurchaseHistoryUseCase
import io.github.faening.lello.core.domain.usecase.store.GetStoreItemsUseCase
import io.github.faening.lello.core.domain.repository.OnboardingPreferencesRepository
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingPreferencesUseCase
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.store.Item
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.PurchaseHistory

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideJournalCategoryUseCases(
        repository: JournalCategoryResources<JournalCategory>
    ) = JournalCategoryUseCase(repository)

    @Provides
    fun provideRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = RewardBalanceUseCase(repository)

    @Provides
    fun provideRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = RewardHistoryUseCase(repository)

    @Provides
    fun provideOnboardingPreferencesUseCase(
        repository: OnboardingPreferencesRepository
    ) = OnboardingPreferencesUseCase(repository)

    @Provides
    fun provideGetMascotStatusUseCase(
        repository: MascotRepository
    ) = GetMascotStatusUseCase(repository)

    @Provides
    fun provideUpdateMascotVitalityUseCase(
        repository: MascotRepository
    ) = UpdateMascotVitalityUseCase(repository)

    @Provides
    fun provideGetMascotVitalityHistoryUseCase(
        repository: MascotRepository
    ) = GetMascotVitalityHistoryUseCase(repository)

    @Provides
    fun provideGetStoreItemsUseCase(
        itemResource: ItemResource<Item>,
        inventoryResource: InventoryResource<InventoryItem>
    ) = GetStoreItemsUseCase(itemResource, inventoryResource)

    @Provides
    fun provideGetInventoryItemsUseCase(
        inventoryResource: InventoryResource<InventoryItem>
    ) = GetInventoryItemsUseCase(inventoryResource)

    @Provides
    fun provideBuyItemUseCase(
        itemResource: ItemResource<Item>,
        inventoryResource: InventoryResource<InventoryItem>,
        purchaseHistoryResource: PurchaseHistoryResource<PurchaseHistory>,
        rewardBalanceUseCase: RewardBalanceUseCase
    ) = BuyItemUseCase(itemResource, inventoryResource, purchaseHistoryResource, rewardBalanceUseCase)

    @Provides
    fun provideGetPurchaseHistoryUseCase(
        purchaseHistoryResource: PurchaseHistoryResource<PurchaseHistory>
    ) = GetPurchaseHistoryUseCase(purchaseHistoryResource)
}