package io.github.faening.lello.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.database.LelloDatabase

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    // region: Journals

    @Provides
    fun provideMoodJournalDao(
        database: LelloDatabase,
    ) = database.moodJournalEntryDao()

    @Provides
    fun provideMealJournalDao(
        database: LelloDatabase,
    ) = database.mealJournalDao()

    @Provides
    fun provideSleepJournalDao(
        database: LelloDatabase,
    ) = database.sleepJournalDao()

    @Provides
    fun provideJournalCategoryDao(
        database: LelloDatabase,
    ) = database.journalCategoryDao()

    // endregion

    // region: Options

    @Provides
    fun provideAppetiteOptionDao(
        database: LelloDatabase,
    ) = database.appetiteOptionDao()

    @Provides
    fun provideClimateDao(
        database: LelloDatabase,
    ) = database.climateOptionDao()

    @Provides
    fun provideDosageFormOptionDao(
        database: LelloDatabase,
    ) = database.dosageFormOptionDao()

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
    fun provideLocationOptionDao(
        database: LelloDatabase,
    ) = database.locationOptionDao()

    @Provides
    fun provideMealOptionDao(
        database: LelloDatabase,
    ) = database.mealOptionDao()

    @Provides
    fun providePortionOptionDao(
        database: LelloDatabase,
    ) = database.portionOptionDao()

    @Provides
    fun provideSleepActivityOptionDao(
        database: LelloDatabase,
    ) = database.sleepActivityOptionDao()

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

    // endregion

    // region: Rewards

    @Provides
    fun provideRewardHistoryDao(
        database: LelloDatabase,
    ) = database.rewardBalanceDao()

    @Provides
    fun provideRewardBalanceDao(
        database: LelloDatabase,
    ) = database.rewardHistoryDao()

    // endregion

    // region: Mascot

    @Provides
    fun provideMascotStatusDao(
        database: LelloDatabase,
    ) = database.mascotStatusDao()

    @Provides
    fun provideMascotVitalityHistoryDao(
        database: LelloDatabase,
    ) = database.mascotVitalityHistoryDao()

    // endregion

    // region: Store

    @Provides
    fun provideItemCatalogDao(
        database: LelloDatabase,
    ) = database.itemCatalogDao()

    @Provides
    fun provideInventoryDao(
        database: LelloDatabase,
    ) = database.inventoryDao()

    @Provides
    fun providePurchaseHistoryDao(
        database: LelloDatabase,
    ) = database.purchaseHistoryDao()

    // endregion
}