package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.DeleteSleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.GetAllSleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.GetSleepQualityOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.SaveSleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.UpdateSleepQualityOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.quality.UpdateSleepQualityOptionUseCase
import io.github.faening.lello.core.model.option.SleepQualityOption

@Module
@InstallIn(SingletonComponent::class)
object SleepQualityOptionUseCaseModule {

    @Provides
    fun provideGetAllSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = GetAllSleepQualityOptionUseCase(repository)

    @Provides
    fun provideGetSleepQualityOptionByIdUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = GetSleepQualityOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = SaveSleepQualityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = UpdateSleepQualityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepQualityOptionActiveStatusUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = UpdateSleepQualityOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSleepQualityOptionUseCase(
        repository: OptionRepository<SleepQualityOption>
    ) = DeleteSleepQualityOptionUseCase(repository)
}
