package io.github.faening.lello.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.DataAppetiteOptionRepository
import io.github.faening.lello.core.data.repository.DataClimateOptionRepository
import io.github.faening.lello.core.data.repository.DataEmotionOptionRepository
import io.github.faening.lello.core.data.repository.DataFoodOptionRepository
import io.github.faening.lello.core.data.repository.DataHealthOptionRepository
import io.github.faening.lello.core.data.repository.DataInventoryRepository
import io.github.faening.lello.core.data.repository.DataItemRepository
import io.github.faening.lello.core.data.repository.DataJournalCategoryRepository
import io.github.faening.lello.core.data.repository.DataJournalSleepRepository
import io.github.faening.lello.core.data.repository.DataLocationOptionRepository
import io.github.faening.lello.core.data.repository.DataMascotRepository
import io.github.faening.lello.core.data.repository.DataMascotStatusRepository
import io.github.faening.lello.core.data.repository.DataMascotVitalityRepository
import io.github.faening.lello.core.data.repository.DataMealJournalRepository
import io.github.faening.lello.core.data.repository.DataMealOptionRepository
import io.github.faening.lello.core.data.repository.DataMedicationActiveIngredientOptionRepository
import io.github.faening.lello.core.data.repository.DataMedicationDosageFormOptionRepository
import io.github.faening.lello.core.data.repository.DataMedicationDosageUnitOptionRepository
import io.github.faening.lello.core.data.repository.DataMedicationJournalRepository
import io.github.faening.lello.core.data.repository.DataMedicationRepository
import io.github.faening.lello.core.data.repository.DataMedicationSkipReasonOptionRepository
import io.github.faening.lello.core.data.repository.DataMoodJournalRepository
import io.github.faening.lello.core.data.repository.DataPortionOptionRepository
import io.github.faening.lello.core.data.repository.DataPurchaseHistoryRepository
import io.github.faening.lello.core.data.repository.DataRewardBalanceRepository
import io.github.faening.lello.core.data.repository.DataRewardHistoryRepository
import io.github.faening.lello.core.data.repository.DataSleepActivityOptionRepository
import io.github.faening.lello.core.data.repository.DataSleepQualityOptionRepository
import io.github.faening.lello.core.data.repository.DataSleepSensationOptionRepository
import io.github.faening.lello.core.data.repository.DataSocialOptionRepository
import io.github.faening.lello.core.data.repository.DataStoreOnboardingRepository
import io.github.faening.lello.core.data.repository.DataStoreUserRepository
import io.github.faening.lello.core.database.dao.AppetiteOptionDao
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.dao.FoodOptionDao
import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.dao.InventoryDao
import io.github.faening.lello.core.database.dao.ItemCatalogDao
import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.dao.MascotStatusDao
import io.github.faening.lello.core.database.dao.MascotVitalityHistoryDao
import io.github.faening.lello.core.database.dao.MealJournalDao
import io.github.faening.lello.core.database.dao.MealOptionDao
import io.github.faening.lello.core.database.dao.MedicationActiveIngredientOptionDao
import io.github.faening.lello.core.database.dao.MedicationDao
import io.github.faening.lello.core.database.dao.MedicationDosageFormOptionDao
import io.github.faening.lello.core.database.dao.MedicationDosageUnitOptionDao
import io.github.faening.lello.core.database.dao.MedicationJournalDao
import io.github.faening.lello.core.database.dao.MedicationSkipReasonOptionDao
import io.github.faening.lello.core.database.dao.MoodJournalDao
import io.github.faening.lello.core.database.dao.PortionOptionDao
import io.github.faening.lello.core.database.dao.PurchaseHistoryDao
import io.github.faening.lello.core.database.dao.RewardBalanceDao
import io.github.faening.lello.core.database.dao.RewardHistoryDao
import io.github.faening.lello.core.database.dao.SleepActivityOptionDao
import io.github.faening.lello.core.database.dao.SleepJournalDao
import io.github.faening.lello.core.database.dao.SleepQualityOptionDao
import io.github.faening.lello.core.database.dao.SleepSensationOptionDao
import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.domain.repository.InventoryRepository
import io.github.faening.lello.core.domain.repository.ItemRepository
import io.github.faening.lello.core.domain.repository.JournalCategoryRepository
import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.domain.repository.MascotStatusRepository
import io.github.faening.lello.core.domain.repository.MascotVitalityRepository
import io.github.faening.lello.core.domain.repository.MealJournalRepository
import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.domain.repository.MoodJournalRepository
import io.github.faening.lello.core.domain.repository.OnboardingRepository
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.repository.PurchaseHistoryRepository
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.domain.repository.UserRepository
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MedicationJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.mascot.MascotStatus
import io.github.faening.lello.core.model.mascot.MascotVitalityHistory
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
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
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
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "lello_preferences")

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideDataAppetiteOptionRepository(
        dao: AppetiteOptionDao
    ): OptionRepository<AppetiteOption> = DataAppetiteOptionRepository(dao)

    @Provides
    fun provideDataClimateOptionRepository(
        dao: ClimateOptionDao
    ): OptionRepository<ClimateOption> = DataClimateOptionRepository(dao)

    @Provides
    fun provideDataEmotionOptionRepository(
        dao: EmotionOptionDao
    ): OptionRepository<EmotionOption> = DataEmotionOptionRepository(dao)

    @Provides
    fun provideDataFoodOptionRepository(
        dao: FoodOptionDao
    ): OptionRepository<FoodOption> = DataFoodOptionRepository(dao)

    @Provides
    fun provideDataHealthOptionRepository(
        dao: HealthOptionDao
    ): OptionRepository<HealthOption> = DataHealthOptionRepository(dao)

    @Provides
    fun provideDataInventoryRepository(
        dao: InventoryDao
    ): InventoryRepository<InventoryItem> = DataInventoryRepository(dao)

    @Provides
    fun provideDataItemRepository(
        dao: ItemCatalogDao
    ): ItemRepository<Item> = DataItemRepository(dao)

    @Provides
    fun provideDataJournalCategoryRepository(
        dao: JournalCategoryDao
    ): JournalCategoryRepository<JournalCategory> = DataJournalCategoryRepository(dao)

    @Provides
    fun provideDataMealJournalRepository(
        dao: MealJournalDao
    ): MealJournalRepository<MealJournal> = DataMealJournalRepository(dao)

    @Provides
    fun provideDataMedicationActiveIngredientOptionRepository(
        dao: MedicationActiveIngredientOptionDao
    ) : OptionRepository<MedicationActiveIngredientOption> = DataMedicationActiveIngredientOptionRepository(dao)

    @Provides
    fun provideDataMedicationRepository(
        dao: MedicationDao
    ): MedicationRepository<Medication> = DataMedicationRepository(dao)

    @Provides
    fun provideDataMedicationDosageFormOptionRepository(
        dao: MedicationDosageFormOptionDao
    ): OptionRepository<MedicationDosageFormOption> = DataMedicationDosageFormOptionRepository(dao)

    @Provides
    fun provideDataMedicationDosageUnitOptionRepository(
        dao: MedicationDosageUnitOptionDao
    ): OptionRepository<MedicationDosageUnitOption> = DataMedicationDosageUnitOptionRepository(dao)

    @Provides
    fun provideDataMedicationJournalRepository(
        dao: MedicationJournalDao
    ): MedicationJournalRepository<MedicationJournal> = DataMedicationJournalRepository(dao)

    @Provides
    fun provideDataMedicationSkipReasonOptionRepository(
        dao: MedicationSkipReasonOptionDao
    ): OptionRepository<MedicationSkipReasonOption> = DataMedicationSkipReasonOptionRepository(dao)

    @Provides
    fun provideDataJournalMoodRepository(
        dao: MoodJournalDao
    ): MoodJournalRepository<MoodJournal> = DataMoodJournalRepository(dao)

    @Provides
    fun provideDataJournalSleepRepository(
        dao: SleepJournalDao
    ): JournalRepository<SleepJournal> = DataJournalSleepRepository(dao)

    @Provides
    fun provideDataLocationOptionRepository(
        dao: LocationOptionDao
    ): OptionRepository<LocationOption> = DataLocationOptionRepository(dao)

    @Provides
    fun provideDataMascotRepository(
        statusDao: MascotStatusDao,
        historyDao: MascotVitalityHistoryDao
    ): MascotRepository = DataMascotRepository(statusDao, historyDao)

    @Provides
    fun provideDataMascotStatusRepository(
        dao: MascotStatusDao
    ): MascotStatusRepository<MascotStatus> = DataMascotStatusRepository(dao)

    @Provides
    fun provideDataMascotVitalityRepository(
        dao: MascotVitalityHistoryDao
    ): MascotVitalityRepository<MascotVitalityHistory> = DataMascotVitalityRepository(dao)

    @Provides
    fun provideDataMealOptionRepository(
        dao: MealOptionDao
    ): OptionRepository<MealOption> = DataMealOptionRepository(dao)

    @Provides
    fun provideDataPortionOptionRepository(
        dao: PortionOptionDao
    ): OptionRepository<PortionOption> = DataPortionOptionRepository(dao)

    @Provides
    fun provideDataPurchaseHistoryRepository(
        dao: PurchaseHistoryDao
    ): PurchaseHistoryRepository<PurchaseHistory> = DataPurchaseHistoryRepository(dao)

    @Provides
    fun provideDataRewardBalanceRepository(
        dao: RewardBalanceDao
    ): RewardBalanceRepository<RewardBalance> = DataRewardBalanceRepository(dao)

    @Provides
    fun provideDataRewardHistoryRepository(
        dao: RewardHistoryDao
    ): RewardHistoryRepository<RewardHistory> = DataRewardHistoryRepository(dao)

    @Provides
    fun provideDataSleepActivityOptionRepository(
        dao: SleepActivityOptionDao
    ): OptionRepository<SleepActivityOption> = DataSleepActivityOptionRepository(dao)

    @Provides
    fun provideDataSleepQualityOptionRepository(
        dao: SleepQualityOptionDao
    ): OptionRepository<SleepQualityOption> = DataSleepQualityOptionRepository(dao)

    @Provides
    fun provideDataSleepSensationOptionRepository(
        dao: SleepSensationOptionDao
    ): OptionRepository<SleepSensationOption> = DataSleepSensationOptionRepository(dao)

    @Provides
    fun provideDataSocialOptionRepository(
        dao: SocialOptionDao
    ): OptionRepository<SocialOption> = DataSocialOptionRepository(dao)

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @Singleton
    fun provideDataStoreOnboardingRepository(
        dataStore: DataStore<Preferences>
    ): OnboardingRepository = DataStoreOnboardingRepository(dataStore)

    @Provides
    @Singleton
    fun provideDataStoreUserRepository(
        dataStore: DataStore<Preferences>
    ): UserRepository = DataStoreUserRepository(dataStore)
}