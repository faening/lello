package io.github.faening.lello.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.DataAppetiteOptionRepository
import io.github.faening.lello.core.data.repository.DataClimateOptionRepository
import io.github.faening.lello.core.data.repository.DataDosageFormOptionRepository
import io.github.faening.lello.core.data.repository.DataEmotionOptionRepository
import io.github.faening.lello.core.data.repository.DataFoodOptionRepository
import io.github.faening.lello.core.data.repository.DataHealthOptionRepository
import io.github.faening.lello.core.data.repository.DataLocationOptionRepository
import io.github.faening.lello.core.data.repository.DataMealOptionRepository
import io.github.faening.lello.core.data.repository.DataPortionOptionRepository
import io.github.faening.lello.core.data.repository.DataSleepActivityOptionRepository
import io.github.faening.lello.core.data.repository.DataSleepQualityOptionRepository
import io.github.faening.lello.core.data.repository.DataSleepSensationOptionRepository
import io.github.faening.lello.core.data.repository.DataSocialOptionRepository
import io.github.faening.lello.core.data.repository.JournalCategoryRepository
import io.github.faening.lello.core.data.repository.MascotRepositoryImpl
import io.github.faening.lello.core.data.repository.MealJournalRepository
import io.github.faening.lello.core.data.repository.MoodJournalRepository
import io.github.faening.lello.core.data.repository.RewardBalanceRepository
import io.github.faening.lello.core.data.repository.RewardHistoryRepository
import io.github.faening.lello.core.data.repository.SleepJournalRepository
import io.github.faening.lello.core.data.repository.store.InventoryRepository
import io.github.faening.lello.core.data.repository.store.ItemRepository
import io.github.faening.lello.core.data.repository.store.PurchaseHistoryRepository
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
import io.github.faening.lello.core.domain.repository.InventoryResource
import io.github.faening.lello.core.domain.repository.ItemResource
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.repository.JournalResources
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.repository.PurchaseHistoryResource
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
import io.github.faening.lello.core.domain.repository.MascotRepository as IMascotRepository
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository as IRewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository as IRewardHistoryRepository

/**
 * Módulo responsável por fornecer as implementações de repositórios para o grafo de dependências do Dagger Hilt.
 *
 * Este módulo existe no módulo `app` pois é o único ponto da aplicação que possui visibilidade tanto para as abstrações definidas na
 * camada `domain`, quanto para as implementações localizadas na camada `data`.
 *
 * Como as boas práticas da Clean Architecture recomendam que a camada `domain` seja independente de não tenha conhecimento sobre `data`,
 * sabemos que `data` também não dependa de `app`, o binding das interfaces de repositório com suas implementações precisa ocorrer aqui.
 *
 * Essa abordagem garante que:
 * - O padrão MVVM e a separação de responsabilidades sejam respeitados.
 * - A manutenção e evolução do projeto sejam mais seguras e organizadas.
 * - Evitamos dependências circulares entre os módulos.
 *
 * Outros desenvolvedores devem utilizar este módulo para registrar novas implementações de repositórios sempre que necessário.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // region: Journals

    @Provides
    fun provideMoodJournalRepository(
        dao: MoodJournalDao
    ): JournalResources<MoodJournal> {
        return MoodJournalRepository(dao)
    }

    @Provides
    fun provideMealJournalRepository(
        dao: MealJournalDao
    ): JournalResources<MealJournal> {
        return MealJournalRepository(dao)
    }

    @Provides
    fun provideSleepJournalRepository(
        dao: SleepJournalDao
    ): JournalResources<SleepJournal> {
        return SleepJournalRepository(dao)
    }

    @Provides
    fun provideJournalCategoryRepository(
        dao: JournalCategoryDao
    ): JournalCategoryResources<JournalCategory> {
        return JournalCategoryRepository(dao)
    }

    // endregion

    // region: Options

    @Provides
    fun provideDataAppetiteOptionRepository(
        dao: AppetiteOptionDao
    ): OptionRepository<AppetiteOption> {
        return DataAppetiteOptionRepository(dao)
    }

    @Provides
    fun provideDataClimateOptionRepository(
        dao: ClimateOptionDao
    ): OptionRepository<ClimateOption> {
        return DataClimateOptionRepository(dao)
    }

    @Provides
    fun provideDataDosageFormOptionRepository(
        dao: DosageFormOptionDao
    ): OptionRepository<DosageFormOption> {
        return DataDosageFormOptionRepository(dao)
    }

    @Provides
    fun provideDataEmotionOptionRepository(
        dao: EmotionOptionDao
    ): OptionRepository<EmotionOption> {
        return DataEmotionOptionRepository(dao)
    }

    @Provides
    fun provideDataFoodOptionRepository(
        dao: FoodOptionDao
    ): OptionRepository<FoodOption> {
        return DataFoodOptionRepository(dao)
    }

    @Provides
    fun provideDataHealthOptionRepository(
        dao: HealthOptionDao
    ): OptionRepository<HealthOption> {
        return DataHealthOptionRepository(dao)
    }

    @Provides
    fun provideDataLocationOptionRepository(
        dao: LocationOptionDao
    ): OptionRepository<LocationOption> {
        return DataLocationOptionRepository(dao)
    }

    @Provides
    fun provideDataMealOptionRepository(
        dao: MealOptionDao
    ): OptionRepository<MealOption> {
        return DataMealOptionRepository(dao)
    }

    @Provides
    fun provideDataPortionOptionRepository(
        dao: PortionOptionDao
    ): OptionRepository<PortionOption> {
        return DataPortionOptionRepository(dao)
    }

    @Provides
    fun provideDataSleepSensationOptionRepository(
        dao: SleepSensationOptionDao
    ): OptionRepository<SleepSensationOption> {
        return DataSleepSensationOptionRepository(dao)
    }

    @Provides
    fun provideDataSleepActivityOptionRepository(
        dao: SleepActivityOptionDao
    ): OptionRepository<SleepActivityOption> {
        return DataSleepActivityOptionRepository(dao)
    }

    @Provides
    fun provideDataSleepQualityOptionRepository(
        dao: SleepQualityOptionDao
    ): OptionRepository<SleepQualityOption> {
        return DataSleepQualityOptionRepository(dao)
    }

    @Provides
    fun provideDataSocialOptionRepository(
        dao: SocialOptionDao
    ): OptionRepository<SocialOption> {
        return DataSocialOptionRepository(dao)
    }

    // endregion

    // region: Rewards

    @Provides
    fun provideRewardHistoryRepository(
        dao: RewardHistoryDao
    ): IRewardHistoryRepository<RewardHistory> {
        return RewardHistoryRepository(dao)
    }

    @Provides
    fun provideRewardBalanceRepository(
        dao: RewardBalanceDao
    ): IRewardBalanceRepository<RewardBalance> {
        return RewardBalanceRepository(dao)
    }

    // endregion

    // region: Mascot

    @Provides
    fun provideMascotRepository(
        statusDao: MascotStatusDao, historyDao: MascotVitalityHistoryDao
    ): IMascotRepository {
        return MascotRepositoryImpl(statusDao, historyDao)
    }

    // endregion

    // region: Store

    @Provides
    fun provideItemRepository(
        dao: ItemCatalogDao
    ): ItemResource<Item> {
        return ItemRepository(dao)
    }

    @Provides
    fun provideInventoryRepository(
        dao: InventoryDao
    ): InventoryResource<InventoryItem> {
        return InventoryRepository(dao)
    }

    @Provides
    fun providePurchaseHistoryRepository(
        dao: PurchaseHistoryDao
    ): PurchaseHistoryResource<PurchaseHistory> {
        return PurchaseHistoryRepository(dao)
    }

    // endregion

    @Provides
    fun provideDataMedicationRepository(
        dao: MedicationDao
    ): MedicationRepository<Medication> {
        return provideDataMedicationRepository(dao)
    }
}