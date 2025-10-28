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
import io.github.faening.lello.core.domain.repository.OptionRepository
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
import io.github.faening.lello.core.domain.usecase.journal.mood.DeleteMoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.mood.GetAllMoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.mood.GetMoodJournalByIdUseCase
import io.github.faening.lello.core.domain.usecase.journal.mood.SaveMoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.DeleteSleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.GetAllSleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.GetSleepJournalByIdUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.SaveSleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotVitalityHistoryUseCase
import io.github.faening.lello.core.domain.usecase.mascot.UpdateMascotVitalityUseCase
import io.github.faening.lello.core.domain.usecase.medication.DisableMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetAllMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetMedicationByIdUseCase
import io.github.faening.lello.core.domain.usecase.medication.SaveMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.UpdateMedicationUseCase
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.DeleteAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.GetAllAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.GetAppetiteOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.SaveAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.UpdateAppetiteOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.UpdateAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.DeleteClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.GetAllClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.GetClimateOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.SaveClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.UpdateClimateOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.UpdateClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.DeleteEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.GetAllEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.GetEmotionOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.SaveEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.UpdateEmotionOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.UpdateEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.DeleteFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.GetAllFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.GetFoodOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.food.SaveFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.UpdateFoodOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.food.UpdateFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.DeleteHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.GetAllHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.GetHealthOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.health.SaveHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.UpdateHealthOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.health.UpdateHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.DeleteLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetAllLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetLocationOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.location.SaveLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.UpdateLocationOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.location.UpdateLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.DeleteMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.GetAllMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.GetMealOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.SaveMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.UpdateMealOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.UpdateMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.DeleteMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetAllMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetMedicationActiveIngredientOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.SaveMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.UpdateMedicationActiveIngredientOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.UpdateMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.DeleteMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetAllMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetMedicationDosageFormOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.SaveMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.UpdateMedicationDosageFormOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.UpdateMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.DeleteMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.GetAllMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.GetMedicationDosageUnitOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.SaveMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.UpdateMedicationDosageUnitOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.UpdateMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.DeletePortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.GetAllPortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.GetPortionOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.SavePortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.UpdatePortionOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.UpdatePortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.DeleteSleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.GetAllSleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.GetSleepActivityOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.SaveSleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.UpdateSleepActivityOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.UpdateSleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.DeleteSleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.GetAllSleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.GetSleepQualityOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.SaveSleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.UpdateSleepQualityOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.UpdateSleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.DeleteSleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.GetAllSleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.GetSleepSensationOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.SaveSleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.UpdateSleepSensationOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.UpdateSleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.DeleteSocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.GetAllSocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.GetSocialOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.social.SaveSocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.UpdateSocialOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.social.UpdateSocialOptionUseCase
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
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.option.AppetiteOption
import io.github.faening.lello.core.model.option.ClimateOption
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.FoodOption
import io.github.faening.lello.core.model.option.HealthOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.MealOption
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import io.github.faening.lello.core.model.option.PortionOption
import io.github.faening.lello.core.model.option.SleepActivityOption
import io.github.faening.lello.core.model.option.SleepQualityOption
import io.github.faening.lello.core.model.option.SleepSensationOption
import io.github.faening.lello.core.model.option.SocialOption
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

    // region: Journal Mood

    @Provides
    fun provideGetAllMoodJournalUseCase(
        repository: JournalRepository<MoodJournal>
    ) = GetAllMoodJournalUseCase(repository)

    @Provides
    fun provideGetMoodJournalByIdUseCase(
        repository: JournalRepository<MoodJournal>
    ) = GetMoodJournalByIdUseCase(repository)

    @Provides
    fun provideSaveMoodJournalUseCase(
        repository: JournalRepository<MoodJournal>,
        rewardCalculatorService: RewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveRewardHistoryUseCase: SaveRewardHistoryUseCase
    ) = SaveMoodJournalUseCase(
        repository,
        rewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase,
        saveRewardHistoryUseCase
    )

    @Provides
    fun provideDeleteMoodJournalUseCase(
        repository: JournalRepository<MoodJournal>
    ) = DeleteMoodJournalUseCase(repository)

    // endregion: Journal Mood

    // region: Journal Sleep

    @Provides
    fun provideGetAllSleepJournalUseCase(
        repository: JournalRepository<SleepJournal>
    ) = GetAllSleepJournalUseCase(repository)

    @Provides
    fun provideGetSleepJournalByIdUseCase(
        repository: JournalRepository<SleepJournal>
    ) = GetSleepJournalByIdUseCase(repository)

    @Provides
    fun provideSaveSleepJournalUseCase(
        repository: JournalRepository<SleepJournal>,
        rewardCalculatorService: RewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveRewardHistoryUseCase: SaveRewardHistoryUseCase
    ) = SaveSleepJournalUseCase(
        repository,
        rewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase,
        saveRewardHistoryUseCase
    )

    @Provides
    fun provideDeleteSleepJournalUseCase(
        repository: JournalRepository<SleepJournal>
    ) = DeleteSleepJournalUseCase(repository)

    // endregion: Journal Sleep

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
    fun provideDisableMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = DisableMedicationUseCase(repository)

    @Provides
    fun provideGetAllMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = GetAllMedicationUseCase(repository)

    @Provides
    fun provideGetMedicationByIdUseCase(
        repository: MedicationRepository<Medication>
    ) = GetMedicationByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = SaveMedicationUseCase(repository)

    @Provides
    fun provideUpdateMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = UpdateMedicationUseCase(repository)

    // endregion: Medication

    // region: Onboarding

    @Provides
    fun provideOnboardingUseCase(
        repository: OnboardingRepository
    ) = OnboardingUseCase(repository)

    // endregion: Onboarding

    // region: Options Appetite

    @Provides
    fun provideGetAllAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = GetAllAppetiteOptionUseCase(repository)

    @Provides
    fun provideGetAppetiteOptionByIdUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = GetAppetiteOptionByIdUseCase(repository)

    @Provides
    fun provideSaveAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = SaveAppetiteOptionUseCase(repository)

    @Provides
    fun provideUpdateAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = UpdateAppetiteOptionUseCase(repository)

    @Provides
    fun provideUpdateAppetiteOptionActiveStatusUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = UpdateAppetiteOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = DeleteAppetiteOptionUseCase(repository)

    // endregion: Options Appetite

    // region: Options Climate

    @Provides
    fun provideGetAllClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = GetAllClimateOptionUseCase(repository)

    @Provides
    fun provideGetClimateOptionByIdUseCase(
        repository: OptionRepository<ClimateOption>
    ) = GetClimateOptionByIdUseCase(repository)

    @Provides
    fun provideSaveClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = SaveClimateOptionUseCase(repository)

    @Provides
    fun provideUpdateClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = UpdateClimateOptionUseCase(repository)

    @Provides
    fun provideUpdateClimateOptionActiveStatusUseCase(
        repository: OptionRepository<ClimateOption>
    ) = UpdateClimateOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = DeleteClimateOptionUseCase(repository)

    // endregion: Options Climate

    // region: Options Emotion

    @Provides
    fun provideGetAllEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = GetAllEmotionOptionUseCase(repository)

    @Provides
    fun provideGetEmotionOptionByIdUseCase(
        repository: OptionRepository<EmotionOption>
    ) = GetEmotionOptionByIdUseCase(repository)

    @Provides
    fun provideSaveEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = SaveEmotionOptionUseCase(repository)

    @Provides
    fun provideUpdateEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = UpdateEmotionOptionUseCase(repository)

    @Provides
    fun provideUpdateEmotionOptionActiveStatusUseCase(
        repository: OptionRepository<EmotionOption>
    ) = UpdateEmotionOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = DeleteEmotionOptionUseCase(repository)

    // endregion: Options Emotion

    // region: Options Food

    @Provides
    fun provideGetAllFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = GetAllFoodOptionUseCase(repository)

    @Provides
    fun provideGetFoodOptionByIdUseCase(
        repository: OptionRepository<FoodOption>
    ) = GetFoodOptionByIdUseCase(repository)

    @Provides
    fun provideSaveFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = SaveFoodOptionUseCase(repository)

    @Provides
    fun provideUpdateFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = UpdateFoodOptionUseCase(repository)

    @Provides
    fun provideUpdateFoodOptionActiveStatusUseCase(
        repository: OptionRepository<FoodOption>
    ) = UpdateFoodOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = DeleteFoodOptionUseCase(repository)

    // endregion: Options Food

    // region: Options Health

    @Provides
    fun provideGetAllHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = GetAllHealthOptionUseCase(repository)

    @Provides
    fun provideGetHealthOptionByIdUseCase(
        repository: OptionRepository<HealthOption>
    ) = GetHealthOptionByIdUseCase(repository)

    @Provides
    fun provideSaveHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = SaveHealthOptionUseCase(repository)

    @Provides
    fun provideUpdateHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = UpdateHealthOptionUseCase(repository)

    @Provides
    fun provideUpdateHealthOptionActiveStatusUseCase(
        repository: OptionRepository<HealthOption>
    ) = UpdateHealthOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = DeleteHealthOptionUseCase(repository)

    // endregion: Options Health

    // region: Options Location

    @Provides
    fun provideGetAllLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = GetAllLocationOptionUseCase(repository)

    @Provides
    fun provideGetLocationOptionByIdUseCase(
        repository: OptionRepository<LocationOption>
    ) = GetLocationOptionByIdUseCase(repository)

    @Provides
    fun provideSaveLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = SaveLocationOptionUseCase(repository)

    @Provides
    fun provideUpdateLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = UpdateLocationOptionUseCase(repository)

    @Provides
    fun provideUpdateLocationOptionActiveStatusUseCase(
        repository: OptionRepository<LocationOption>
    ) = UpdateLocationOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = DeleteLocationOptionUseCase(repository)

    // endregion: Options Location

    // region: Options Meal

    @Provides
    fun provideGetAllMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = GetAllMealOptionUseCase(repository)

    @Provides
    fun provideGetMealOptionByIdUseCase(
        repository: OptionRepository<MealOption>
    ) = GetMealOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = SaveMealOptionUseCase(repository)

    @Provides
    fun provideUpdateMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = UpdateMealOptionUseCase(repository)

    @Provides
    fun provideUpdateMealOptionActiveStatusUseCase(
        repository: OptionRepository<MealOption>
    ) = UpdateMealOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = DeleteMealOptionUseCase(repository)

    // endregion: Options Meal

    // region: Options Medication Active Ingredient

    @Provides
    fun provideGetAllMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = GetAllMedicationActiveIngredientOptionUseCase(repository)

    @Provides
    fun provideGetMedicationActiveIngredientOptionByIdUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = GetMedicationActiveIngredientOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = SaveMedicationActiveIngredientOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = UpdateMedicationActiveIngredientOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationActiveIngredientOptionActiveStatusUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = UpdateMedicationActiveIngredientOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = DeleteMedicationActiveIngredientOptionUseCase(repository)

    // endregion: Options Medication Active Ingredient
    
    // region: Options Medication Dosage Form

    @Provides
    fun provideGetAllMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = GetAllMedicationDosageFormOptionUseCase(repository)

    @Provides
    fun provideGetMedicationDosageFormOptionByIdUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = GetMedicationDosageFormOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = SaveMedicationDosageFormOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = UpdateMedicationDosageFormOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageFormOptionActiveStatusUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = UpdateMedicationDosageFormOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = DeleteMedicationDosageFormOptionUseCase(repository)

    // endregion: Options Medication Dosage Form

    // region: Options Medication Dosage Unit

    @Provides
    fun provideGetAllMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = GetAllMedicationDosageUnitOptionUseCase(repository)

    @Provides
    fun provideGetMedicationDosageUnitOptionByIdUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = GetMedicationDosageUnitOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = SaveMedicationDosageUnitOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = UpdateMedicationDosageUnitOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageUnitOptionActiveStatusUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = UpdateMedicationDosageUnitOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = DeleteMedicationDosageUnitOptionUseCase(repository)

    // endregion: Options Medication Dosage Unit
    
    // region: Options Portion

    @Provides
    fun provideGetAllPortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = GetAllPortionOptionUseCase(repository)

    @Provides
    fun provideGetPortionOptionByIdUseCase(
        repository: OptionRepository<PortionOption>
    ) = GetPortionOptionByIdUseCase(repository)

    @Provides
    fun provideSavePortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = SavePortionOptionUseCase(repository)

    @Provides
    fun provideUpdatePortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = UpdatePortionOptionUseCase(repository)

    @Provides
    fun provideUpdatePortionOptionActiveStatusUseCase(
        repository: OptionRepository<PortionOption>
    ) = UpdatePortionOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeletePortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = DeletePortionOptionUseCase(repository)

    // endregion: Options Portion

    // region: Options Sleep Activity

    @Provides
    fun provideGetAllSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = GetAllSleepActivityOptionUseCase(repository)

    @Provides
    fun provideGetSleepActivityOptionByIdUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = GetSleepActivityOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = SaveSleepActivityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = UpdateSleepActivityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepActivityOptionActiveStatusUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = UpdateSleepActivityOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = DeleteSleepActivityOptionUseCase(repository)

    // endregion: Options SleepActivity

    // region: Options Sleep Quality

    @Provides
    fun provideGetAllSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = GetAllSleepQualityOptionUseCase(repository)

    @Provides
    fun provideGetSleepQualityOptionByIdUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = GetSleepQualityOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = SaveSleepQualityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = UpdateSleepQualityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepQualityOptionActiveStatusUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = UpdateSleepQualityOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = DeleteSleepQualityOptionUseCase(repository)

    // endregion: Options Sleep Quality

    // region: Options Sleep Sensation

    @Provides
    fun provideGetAllSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = GetAllSleepSensationOptionUseCase(repository)

    @Provides
    fun provideGetSleepSensationOptionByIdUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = GetSleepSensationOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = SaveSleepSensationOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = UpdateSleepSensationOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepSensationOptionActiveStatusUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = UpdateSleepSensationOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = DeleteSleepSensationOptionUseCase(repository)

    // endregion: Options Sleep Sensation

    // region: Options Social

    @Provides
    fun provideGetAllSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = GetAllSocialOptionUseCase(repository)

    @Provides
    fun provideGetSocialOptionByIdUseCase(
        repository: OptionRepository<SocialOption>
    ) = GetSocialOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = SaveSocialOptionUseCase(repository)

    @Provides
    fun provideUpdateSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = UpdateSocialOptionUseCase(repository)

    @Provides
    fun provideUpdateSocialOptionActiveStatusUseCase(
        repository: OptionRepository<SocialOption>
    ) = UpdateSocialOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = DeleteSocialOptionUseCase(repository)

    // endregion: Options Social

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