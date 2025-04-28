@file:Suppress("unused")

package io.github.faening.lello.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.DiaryRepository
import io.github.faening.lello.core.data.repository.ResourceRepository
import io.github.faening.lello.core.model.diary.Diary

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindDiaryRepository(
        diaryRepository: DiaryRepository
    ): ResourceRepository<Diary>
}