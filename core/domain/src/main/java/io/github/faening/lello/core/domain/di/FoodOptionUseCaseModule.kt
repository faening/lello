package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.food.DeleteFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.GetAllFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.GetFoodOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.food.SaveFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.UpdateFoodOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.food.UpdateFoodOptionUseCase
import io.github.faening.lello.core.model.option.FoodOption

@Module
@InstallIn(SingletonComponent::class)
object FoodOptionUseCaseModule {

    @Provides
    fun provideGetAllFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = GetAllFoodOptionUseCase(repository)

    @Provides
    fun provideGetFoodOptionByIdUseCase(
        repository: OptionRepository<FoodOption>
    ) = GetFoodOptionByIdUseCase(repository)

    @Provides
    fun provideSaveFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = SaveFoodOptionUseCase(repository)

    @Provides
    fun provideUpdateFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = UpdateFoodOptionUseCase(repository)

    @Provides
    fun provideUpdateFoodOptionActiveStatusUseCase(
        repository: OptionRepository<FoodOption>
    ) = UpdateFoodOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteFoodOptionUseCase(
        repository: OptionRepository<FoodOption>
    ) = DeleteFoodOptionUseCase(repository)
}
