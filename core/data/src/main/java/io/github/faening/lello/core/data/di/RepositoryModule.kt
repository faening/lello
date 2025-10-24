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
import io.github.faening.lello.core.data.repository.DataDosageFormOptionRepository
import io.github.faening.lello.core.data.repository.DataEmotionOptionRepository
import io.github.faening.lello.core.data.repository.DataFoodOptionRepository
import io.github.faening.lello.core.data.repository.DataHealthOptionRepository
import io.github.faening.lello.core.data.repository.DataInventoryRepository
import io.github.faening.lello.core.data.repository.DataItemRepository
import io.github.faening.lello.core.data.repository.DataLocationOptionRepository
import io.github.faening.lello.core.data.repository.DataMealOptionRepository
import io.github.faening.lello.core.data.repository.DataMedicationRepository
import io.github.faening.lello.core.data.repository.DataPortionOptionRepository
import io.github.faening.lello.core.data.repository.DataPurchaseHistoryRepository
import io.github.faening.lello.core.data.repository.DataSleepActivityOptionRepository
import io.github.faening.lello.core.data.repository.DataSleepQualityOptionRepository
import io.github.faening.lello.core.data.repository.DataSleepSensationOptionRepository
import io.github.faening.lello.core.data.repository.DataSocialOptionRepository
import io.github.faening.lello.core.data.repository.DataStoreOnboardingRepository
import io.github.faening.lello.core.data.repository.DataStoreUserRepository
import io.github.faening.lello.core.data.repository.JournalCategoryRepository
import io.github.faening.lello.core.data.repository.MascotRepositoryImpl
import io.github.faening.lello.core.data.repository.MealJournalRepository
import io.github.faening.lello.core.data.repository.MoodJournalRepository
import io.github.faening.lello.core.data.repository.RewardBalanceRepository
import io.github.faening.lello.core.data.repository.RewardHistoryRepository
import io.github.faening.lello.core.data.repository.SleepJournalRepository
import io.github.faening.lello.core.database.dao.AppetiteOptionDao
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.DosageFormOptionDao
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
import io.github.faening.lello.core.database.dao.MedicationDao
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
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.repository.JournalResources
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.domain.repository.OnboardingRepository
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.repository.PurchaseHistoryRepository
import io.github.faening.lello.core.domain.repository.UserRepository
import io.github.faening.lello.core.model.Medication
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.option.AppetiteOption
import io.github.faening.lello.core.model.option.ClimateOption
import io.github.faening.lello.core.model.option.DosageFormOption
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.FoodOption
import io.github.faening.lello.core.model.option.HealthOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.MealOption
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
import io.github.faening.lello.core.domain.repository.MascotRepository as IMascotRepository
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository as IRewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository as IRewardHistoryRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "lello_preferences")

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
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
    ) : UserRepository = DataStoreUserRepository(dataStore)

    @Provides
    fun provideMoodJournalRepository(
        dao: MoodJournalDao
    ): JournalResources<MoodJournal> = MoodJournalRepository(dao)

    @Provides
    fun provideMealJournalRepository(
        dao: MealJournalDao
    ): JournalResources<MealJournal> = MealJournalRepository(dao)

    @Provides
    fun provideSleepJournalRepository(
        dao: SleepJournalDao
    ): JournalResources<SleepJournal> = SleepJournalRepository(dao)

    @Provides
    fun provideJournalCategoryRepository(
        dao: JournalCategoryDao
    ): JournalCategoryResources<JournalCategory> = JournalCategoryRepository(dao)

    @Provides
    fun provideDataAppetiteOptionRepository(
        dao: AppetiteOptionDao
    ): OptionRepository<AppetiteOption> = DataAppetiteOptionRepository(dao)

    @Provides
    fun provideDataClimateOptionRepository(
        dao: ClimateOptionDao
    ): OptionRepository<ClimateOption> = DataClimateOptionRepository(dao)

    @Provides
    fun provideDataDosageFormOptionRepository(
        dao: DosageFormOptionDao
    ): OptionRepository<DosageFormOption> = DataDosageFormOptionRepository(dao)

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
    fun provideDataLocationOptionRepository(
        dao: LocationOptionDao
    ): OptionRepository<LocationOption> = DataLocationOptionRepository(dao)

    @Provides
    fun provideDataMealOptionRepository(
        dao: MealOptionDao
    ): OptionRepository<MealOption> = DataMealOptionRepository(dao)

    @Provides
    fun provideDataPortionOptionRepository(
        dao: PortionOptionDao
    ): OptionRepository<PortionOption> = DataPortionOptionRepository(dao)

    @Provides
    fun provideDataSleepSensationOptionRepository(
        dao: SleepSensationOptionDao
    ): OptionRepository<SleepSensationOption> = DataSleepSensationOptionRepository(dao)

    @Provides
    fun provideDataSleepActivityOptionRepository(
        dao: SleepActivityOptionDao
    ): OptionRepository<SleepActivityOption> = DataSleepActivityOptionRepository(dao)

    @Provides
    fun provideDataSleepQualityOptionRepository(
        dao: SleepQualityOptionDao
    ): OptionRepository<SleepQualityOption> = DataSleepQualityOptionRepository(dao)

    @Provides
    fun provideDataSocialOptionRepository(
        dao: SocialOptionDao
    ): OptionRepository<SocialOption> = DataSocialOptionRepository(dao)

    @Provides
    fun provideRewardHistoryRepository(
        dao: RewardHistoryDao
    ): IRewardHistoryRepository<RewardHistory> = RewardHistoryRepository(dao)

    @Provides
    fun provideRewardBalanceRepository(
        dao: RewardBalanceDao
    ): IRewardBalanceRepository<RewardBalance> = RewardBalanceRepository(dao)

    @Provides
    fun provideMascotRepository(
        statusDao: MascotStatusDao,
        historyDao: MascotVitalityHistoryDao
    ): IMascotRepository = MascotRepositoryImpl(statusDao, historyDao)

    @Provides
    fun provideDataItemRepository(
        dao: ItemCatalogDao
    ): ItemRepository<Item> = DataItemRepository(dao)

    @Provides
    fun provideDataInventoryRepository(
        dao: InventoryDao
    ): InventoryRepository<InventoryItem> = DataInventoryRepository(dao)

    @Provides
    fun provideDataPurchaseHistoryRepository(
        dao: PurchaseHistoryDao
    ): PurchaseHistoryRepository<PurchaseHistory> = DataPurchaseHistoryRepository(dao)

    @Provides
    fun provideDataMedicationRepository(
        dao: MedicationDao
    ): MedicationRepository<Medication> = DataMedicationRepository(dao)
}