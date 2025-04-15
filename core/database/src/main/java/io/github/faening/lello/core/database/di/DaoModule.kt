package io.github.faening.lello.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.database.LelloDatabase
import io.github.faening.lello.core.database.dao.DiaryDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun providesDiaryDao(
        database: LelloDatabase,
    ): DiaryDao = database.diaryDao()


}