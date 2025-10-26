package io.github.faening.lello.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.database.LelloDatabase

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun provideAnvisaMedicationDao(
        database: LelloDatabase,
    ) = database.anvisaMedicationDao()

    @Provides
    fun provideAppetiteOptionDao(
        database: LelloDatabase,
    ) = database.appetiteOptionDao()

    @Provides
    fun provideClimateDao(
        database: LelloDatabase,
    ) = database.climateOptionDao()

    @Provides
    fun provideEmotionDao(
        database: LelloDatabase,
    ) = database.emotionOptionDao()

    @Provides
    fun provideFoodOptionDao(
        database: LelloDatabase,
    ) = database.foodOptionDao()

    @Provides
    fun provideHealthOptionDao(
        database: LelloDatabase,
    ) = database.healthOptionDao()

    @Provides
    fun provideInventoryDao(
        database: LelloDatabase,
    ) = database.inventoryDao()

    @Provides
    fun provideItemCatalogDao(
        database: LelloDatabase,
    ) = database.itemCatalogDao()

    @Provides
    fun provideJournalCategoryDao(
        database: LelloDatabase,
    ) = database.journalCategoryDao()

    @Provides
    fun provideLocationOptionDao(
        database: LelloDatabase,
    ) = database.locationOptionDao()

    @Provides
    fun provideMascotStatusDao(
        database: LelloDatabase,
    ) = database.mascotStatusDao()

    @Provides
    fun provideMascotVitalityHistoryDao(
        database: LelloDatabase,
    ) = database.mascotVitalityHistoryDao()

    @Provides
    fun provideMealJournalDao(
        database: LelloDatabase,
    ) = database.mealJournalDao()

    @Provides
    fun provideMedicationActiveIngredientOptionDao(
        database: LelloDatabase,
    ) = database.medicationActiveIngredientOptionDao()

    @Provides
    fun provideMedicationDosageFormOptionDao(
        database: LelloDatabase,
    ) = database.medicationDosageFormOptionDao()

    @Provides
    fun provideMedicationDosageUnitOptionDao(
        database: LelloDatabase,
    ) = database.medicationDosageUnitOptionDao()

    @Provides
    fun provideMealOptionDao(
        database: LelloDatabase,
    ) = database.mealOptionDao()

    @Provides
    fun provideMoodJournalDao(
        database: LelloDatabase,
    ) = database.moodJournalEntryDao()

    @Provides
    fun providePortionOptionDao(
        database: LelloDatabase,
    ) = database.portionOptionDao()

    @Provides
    fun providePurchaseHistoryDao(
        database: LelloDatabase,
    ) = database.purchaseHistoryDao()

    @Provides
    fun provideRewardBalanceDao(
        database: LelloDatabase,
    ) = database.rewardBalanceDao()

    @Provides
    fun provideRewardHistoryDao(
        database: LelloDatabase,
    ) = database.rewardHistoryDao()

    @Provides
    fun provideSleepActivityOptionDao(
        database: LelloDatabase,
    ) = database.sleepActivityOptionDao()

    @Provides
    fun provideSleepJournalDao(
        database: LelloDatabase,
    ) = database.sleepJournalDao()

    @Provides
    fun provideSleepQualityOptionDao(
        database: LelloDatabase,
    ) = database.sleepQualityOptionDao()

    @Provides
    fun provideSleepSensationOptionDao(
        database: LelloDatabase,
    ) = database.sleepSensationOptionDao()

    @Provides
    fun provideSocialOptionDao(
        database: LelloDatabase,
    ) = database.socialOptionDao()
}