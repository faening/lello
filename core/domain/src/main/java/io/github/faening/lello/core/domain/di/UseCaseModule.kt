package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.domain.repository.OnboardingRepository
import io.github.faening.lello.core.domain.repository.PurchaseHistoryResource
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.domain.repository.UserRepository
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotVitalityHistoryUseCase
import io.github.faening.lello.core.domain.usecase.mascot.UpdateMascotVitalityUseCase
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingUseCase
import io.github.faening.lello.core.domain.usecase.options.JournalCategoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.store.BuyItemUseCase
import io.github.faening.lello.core.domain.usecase.store.GetInventoryItemsUseCase
import io.github.faening.lello.core.domain.usecase.store.GetPurchaseHistoryUseCase
import io.github.faening.lello.core.domain.usecase.store.GetStoreItemsUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import io.github.faening.lello.core.domain.usecase.user.SaveUserEmailUseCase
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.Item
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
    fun provideOnboardingUseCase(
        repository: OnboardingRepository
    ) = OnboardingUseCase(repository)

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
        itemRepository: ItemRepository<Item>,
        inventoryRepository: InventoryRepository<InventoryItem>
    ) = GetStoreItemsUseCase(itemRepository, inventoryRepository)

    @Provides
    fun provideGetInventoryItemsUseCase(
        inventoryRepository: InventoryRepository<InventoryItem>
    ) = GetInventoryItemsUseCase(inventoryRepository)

    @Provides
    fun provideBuyItemUseCase(
        itemRepository: ItemRepository<Item>,
        inventoryRepository: InventoryRepository<InventoryItem>,
        purchaseHistoryResource: PurchaseHistoryResource<PurchaseHistory>,
        rewardBalanceUseCase: RewardBalanceUseCase
    ) = BuyItemUseCase(itemRepository, inventoryRepository, purchaseHistoryResource, rewardBalanceUseCase)

    @Provides
    fun provideGetPurchaseHistoryUseCase(
        purchaseHistoryResource: PurchaseHistoryResource<PurchaseHistory>
    ) = GetPurchaseHistoryUseCase(purchaseHistoryResource)

    // region: User

    @Provides
    fun provideGetUserEmailUseCase(
        repository: UserRepository
    ) = GetUserEmailUseCase(repository)

    fun provideSaveUserEmailUseCase(
        repository: UserRepository
    ) = SaveUserEmailUseCase(repository)

    // endregion: User
}