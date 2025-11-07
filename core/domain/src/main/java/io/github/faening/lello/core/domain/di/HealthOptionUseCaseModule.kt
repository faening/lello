package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.health.DeleteHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.GetAllHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.GetHealthOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.health.SaveHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.UpdateHealthOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.health.UpdateHealthOptionUseCase
import io.github.faening.lello.core.model.option.HealthOption

@Module
@InstallIn(SingletonComponent::class)
object HealthOptionUseCaseModule {

    @Provides
    fun provideGetAllHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = GetAllHealthOptionUseCase(repository)

    @Provides
    fun provideGetHealthOptionByIdUseCase(
        repository: OptionRepository<HealthOption>
    ) = GetHealthOptionByIdUseCase(repository)

    @Provides
    fun provideSaveHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = SaveHealthOptionUseCase(repository)

    @Provides
    fun provideUpdateHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = UpdateHealthOptionUseCase(repository)

    @Provides
    fun provideUpdateHealthOptionActiveStatusUseCase(
        repository: OptionRepository<HealthOption>
    ) = UpdateHealthOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteHealthOptionUseCase(
        repository: OptionRepository<HealthOption>
    ) = DeleteHealthOptionUseCase(repository)
}
