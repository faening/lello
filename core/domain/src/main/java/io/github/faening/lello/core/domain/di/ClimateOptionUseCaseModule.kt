package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.climate.DeleteClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.GetAllClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.GetClimateOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.SaveClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.UpdateClimateOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.UpdateClimateOptionUseCase
import io.github.faening.lello.core.model.option.ClimateOption

@Module
@InstallIn(SingletonComponent::class)
object ClimateOptionUseCaseModule {

    @Provides
    fun provideGetAllClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = GetAllClimateOptionUseCase(repository)

    @Provides
    fun provideGetClimateOptionByIdUseCase(
        repository: OptionRepository<ClimateOption>
    ) = GetClimateOptionByIdUseCase(repository)

    @Provides
    fun provideSaveClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = SaveClimateOptionUseCase(repository)

    @Provides
    fun provideUpdateClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = UpdateClimateOptionUseCase(repository)

    @Provides
    fun provideUpdateClimateOptionActiveStatusUseCase(
        repository: OptionRepository<ClimateOption>
    ) = UpdateClimateOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteClimateOptionUseCase(
        repository: OptionRepository<ClimateOption>
    ) = DeleteClimateOptionUseCase(repository)
}
