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
    fun provideClimateDao(
        database: LelloDatabase,
    ) = database.climateOptionDao()

    @Provides
    fun provideEmotionDao(
        database: LelloDatabase,
    ) = database.emotionOptionDao()

    @Provides
    fun provideJournalDao(
        database: LelloDatabase,
    ) = database.JournalDao()

    @Provides
    fun provideLocationOptionDao(
        database: LelloDatabase,
    ) = database.locationOptionDao()
}