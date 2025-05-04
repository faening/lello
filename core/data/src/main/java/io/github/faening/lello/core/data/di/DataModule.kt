package io.github.faening.lello.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.JournalCategoryRepository
import io.github.faening.lello.core.database.dao.JournalCategoryDao

@Module
@InstallIn(SingletonComponent::class)
object ProvidesDataModule {

    @Provides
    fun provideJournalCategoryRepository(
        dao: JournalCategoryDao
    ): JournalCategoryRepository {
        return JournalCategoryRepository(dao)
    }
}

// @Module
// @InstallIn(SingletonComponent::class)
// abstract class BindsDataModule { }