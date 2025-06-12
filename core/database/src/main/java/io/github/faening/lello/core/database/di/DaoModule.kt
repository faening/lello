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
    fun provideClimateDao(
        database: LelloDatabase,
    ) = database.climateOptionDao()

    @Provides
    fun provideEmotionDao(
        database: LelloDatabase,
    ) = database.emotionOptionDao()

    @Provides
    fun provideHealthOptionDao(
        database: LelloDatabase,
    ) = database.healthOptionDao()

    @Provides
    fun provideLocationOptionDao(
        database: LelloDatabase,
    ) = database.locationOptionDao()

    @Provides
    fun provideSensationOptionDao(
        database: LelloDatabase,
    ) = database.sensationOptionDao()

    @Provides
    fun provideSocialOptionDao(
        database: LelloDatabase,
    ) = database.socialOptionDao()

    // endregion
}