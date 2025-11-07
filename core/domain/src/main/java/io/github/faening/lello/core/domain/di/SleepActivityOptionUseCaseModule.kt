package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.DeleteSleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.GetAllSleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.GetSleepActivityOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.SaveSleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.UpdateSleepActivityOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.sleep.activity.UpdateSleepActivityOptionUseCase
import io.github.faening.lello.core.model.option.SleepActivityOption

@Module
@InstallIn(SingletonComponent::class)
object SleepActivityOptionUseCaseModule {

    @Provides
    fun provideGetAllSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = GetAllSleepActivityOptionUseCase(repository)

    @Provides
    fun provideGetSleepActivityOptionByIdUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = GetSleepActivityOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = SaveSleepActivityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = UpdateSleepActivityOptionUseCase(repository)

    @Provides
    fun provideUpdateSleepActivityOptionActiveStatusUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = UpdateSleepActivityOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSleepActivityOptionUseCase(
        repository: OptionRepository<SleepActivityOption>
    ) = DeleteSleepActivityOptionUseCase(repository)
}
