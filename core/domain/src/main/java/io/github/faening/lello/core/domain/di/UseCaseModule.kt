package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.DailyCheckInRepository
import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.domain.repository.JournalCategoryRepository
import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.domain.repository.OnboardingRepository
import io.github.faening.lello.core.domain.repository.PurchaseHistoryRepository
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.domain.repository.UserRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.category.GetJournalCategoriesUseCase
import io.github.faening.lello.core.domain.usecase.journal.category.GetJournalCategoryByIdUseCase
import io.github.faening.lello.core.domain.usecase.journal.meal.DeleteMealJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.meal.GetAllMealJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.meal.GetMealJournalByIdUseCase
import io.github.faening.lello.core.domain.usecase.journal.meal.SaveMealJournalUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotVitalityHistoryUseCase
import io.github.faening.lello.core.domain.usecase.mascot.UpdateMascotVitalityUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetMedicationByIdUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetMedicationByProductNameUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetMedicationByTherapeuticClassUseCase
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingUseCase
import io.github.faening.lello.core.domain.usecase.reward.GetDailyCheckInUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.ClearRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.ObserveRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.ClearRewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.GetRewardAmountByOriginUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.GetRewardHistoryByOriginUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.GetRewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.ObserveRewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.store.BuyItemUseCase
import io.github.faening.lello.core.domain.usecase.store.GetInventoryItemsUseCase
import io.github.faening.lello.core.domain.usecase.store.GetPurchaseHistoryUseCase
import io.github.faening.lello.core.domain.usecase.store.GetStoreItemsUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import io.github.faening.lello.core.domain.usecase.user.SaveUserEmailUseCase
import io.github.faening.lello.core.domain.usecase.user.SetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.model.Medication
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.store.InventoryItem
import io.github.faening.lello.core.model.store.Item
import io.github.faening.lello.core.model.store.PurchaseHistory
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    // region: Journal Category

    @Provides
    fun provideGetJournalCategoriesUseCase(
        repository: JournalCategoryRepository<JournalCategory>
    ) = GetJournalCategoriesUseCase(repository)

    @Provides
    fun provideGetJournalCategoryByIdUseCase(
        repository: JournalCategoryRepository<JournalCategory>
    ) = GetJournalCategoryByIdUseCase(repository)

    // endregion: Journal Category

    // region: Journal Meal

    @Provides
    fun provideGetAllMealJournalUseCase(
        repository: JournalRepository<MealJournal>
    ) = GetAllMealJournalUseCase(repository)

    @Provides
    fun provideGetMealJournalByIdUseCase(
        repository: JournalRepository<MealJournal>
    ) = GetMealJournalByIdUseCase(repository)

    @Provides
    fun provideSaveMealJournalUseCase(
        repository: JournalRepository<MealJournal>,
        rewardCalculatorService: RewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveRewardHistoryUseCase: SaveRewardHistoryUseCase
    ) = SaveMealJournalUseCase(
        repository,
        rewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase,
        saveRewardHistoryUseCase
    )

    @Provides
    fun provideDeleteMealJournalUseCase(
        repository: JournalRepository<MealJournal>
    ) = DeleteMealJournalUseCase(repository)

    // endregion: Journal Meal

    // region: Mascot

    @Provides
    fun provideGetMascotStatusUseCase(
        repository: MascotRepository
    ) = GetMascotStatusUseCase(repository)

    @Provides
    fun provideGetMascotVitalityHistoryUseCase(
        repository: MascotRepository
    ) = GetMascotVitalityHistoryUseCase(repository)

    @Provides
    fun provideUpdateMascotVitalityUseCase(
        repository: MascotRepository
    ) = UpdateMascotVitalityUseCase(repository)

    // endregion: Mascot

    // region: Medication

    @Provides
    fun provideGetMedicationByIdUseCase(
        repository: MedicationRepository<Medication>
    ) = GetMedicationByIdUseCase(repository)

    @Provides
    fun provideGetMedicationByProductNameUseCase(
        repository: MedicationRepository<Medication>
    ) = GetMedicationByProductNameUseCase(repository)

    @Provides
    fun provideGetMedicationByTherapeuticClassUseCase(
        repository: MedicationRepository<Medication>
    ) = GetMedicationByTherapeuticClassUseCase(repository)

    // endregion: Medication

    // region: Onboarding

    @Provides
    fun provideOnboardingUseCase(
        repository: OnboardingRepository
    ) = OnboardingUseCase(repository)

    // endregion: Onboarding

    // region: Reward

    @Provides
    @Singleton
    fun provideDailyCheckInRepository(
        useCase: GetDailyCheckInUseCase
    ): DailyCheckInRepository = useCase

    @Provides
    fun provideClearRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = ClearRewardBalanceUseCase(repository)

    @Provides
    fun provideGetRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = GetRewardBalanceUseCase(repository)

    @Provides
    fun provideObserveRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = ObserveRewardBalanceUseCase(repository)

    @Provides
    fun provideSaveOrUpdateRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = SaveOrUpdateRewardBalanceUseCase(repository)

    @Provides
    fun provideObserveRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = ObserveRewardHistoryUseCase(repository)

    @Provides
    fun provideGetRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = GetRewardHistoryUseCase(repository)

    @Provides
    fun provideGetRewardHistoryByOriginUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = GetRewardHistoryByOriginUseCase(repository)

    @Provides
    fun provideGetRewardAmountByOriginUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = GetRewardAmountByOriginUseCase(repository)

    @Provides
    fun provideSaveRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = SaveRewardHistoryUseCase(repository)

    @Provides
    fun provideClearRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = ClearRewardHistoryUseCase(repository)

    // endregion: Reward

    // region: Store

    @Provides
    fun provideBuyItemUseCase(
        itemRepository: ItemRepository<Item>,
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
        itemRepository: ItemRepository<Item>,
        inventoryRepository: InventoryRepository<InventoryItem>
    ) = GetStoreItemsUseCase(itemRepository, inventoryRepository)

    // endregion: Store

    // region: User

    @Provides
    fun provideGetUserBiometricPreferencesUseCase(
        repository: UserRepository
    ) = GetUserBiometricPreferencesUseCase(repository)

    @Provides
    fun provideGetUserEmailUseCase(
        repository: UserRepository
    ) = GetUserEmailUseCase(repository)

    @Provides
    fun provideSaveUserEmailUseCase(
        repository: UserRepository
    ) = SaveUserEmailUseCase(repository)

    @Provides
    fun provideSetUserBiometricPreferencesUseCase(
        repository: UserRepository
    ) = SetUserBiometricPreferencesUseCase(repository)

    // endregion: User
}