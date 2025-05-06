package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.usecase.JournalCategoryUseCase
import io.github.faening.lello.core.model.journal.JournalCategory

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideJournalCategoryUseCases(
        repository: JournalCategoryResources<JournalCategory>
    ) = JournalCategoryUseCase(repository)
}