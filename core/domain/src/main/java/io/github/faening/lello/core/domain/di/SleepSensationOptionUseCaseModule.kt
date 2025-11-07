package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.DeleteSleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.GetAllSleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.GetSleepSensationOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.SaveSleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.UpdateSleepSensationOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.sensation.UpdateSleepSensationOptionUseCase
import io.github.faening.lello.core.model.option.SleepSensationOption

@Module
@InstallIn(SingletonComponent::class)
object SleepSensationOptionUseCaseModule {

    @Provides
    fun provideGetAllSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = GetAllSleepSensationOptionUseCase(repository)

    @Provides
    fun provideGetSleepSensationOptionByIdUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = GetSleepSensationOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = SaveSleepSensationOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = UpdateSleepSensationOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepSensationOptionActiveStatusUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = UpdateSleepSensationOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSleepSensationOptionUseCase(
        repository: OptionRepository<SleepSensationOption>
    ) = DeleteSleepSensationOptionUseCase(repository)
}
