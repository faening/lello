package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.JournalCategoryRepository
import io.github.faening.lello.core.domain.usecase.journal.category.GetJournalCategoriesUseCase
import io.github.faening.lello.core.domain.usecase.journal.category.GetJournalCategoryByIdUseCase
import io.github.faening.lello.core.model.journal.JournalCategory

@Module
@InstallIn(SingletonComponent::class)
object JournalCategoryUseCaseModule {

    @Provides
    fun provideGetJournalCategoriesUseCase(
        repository: JournalCategoryRepository<JournalCategory>
    ) = GetJournalCategoriesUseCase(repository)

    @Provides
    fun provideGetJournalCategoryByIdUseCase(
        repository: JournalCategoryRepository<JournalCategory>
    ) = GetJournalCategoryByIdUseCase(repository)
}
