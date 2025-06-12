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
    
    fun provideSensationOptionDao(
        database: LelloDatabase,
    ) = database.sensationOptionDao()

    @Provides
    fun provideSleepActivityOptionDao(
        database: LelloDatabase,
    ) = database.sleepActivityOptionDao()

    @Provides
    fun provideSleepQualityOptionDao(
        database: LelloDatabase,
    ) = database.sleepQualityOptionDao()

    @Provides
    fun provideSocialOptionDao(
        database: LelloDatabase,
    ) = database.socialOptionDao()

    // endregion
}