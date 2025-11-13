package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.meal.DeleteMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.GetAllMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.GetMealOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.SaveMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.UpdateMealOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.UpdateMealOptionUseCase
import io.github.faening.lello.core.model.option.MealOption

@Module
@InstallIn(SingletonComponent::class)
object MealOptionUseCaseModule {

    @Provides
    fun provideGetAllMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = GetAllMealOptionUseCase(repository)

    @Provides
    fun provideGetMealOptionByIdUseCase(
        repository: OptionRepository<MealOption>
    ) = GetMealOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = SaveMealOptionUseCase(repository)

    @Provides
    fun provideUpdateMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = UpdateMealOptionUseCase(repository)

    @Provides
    fun provideUpdateMealOptionActiveStatusUseCase(
        repository: OptionRepository<MealOption>
    ) = UpdateMealOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMealOptionUseCase(
        repository: OptionRepository<MealOption>
    ) = DeleteMealOptionUseCase(repository)
}
