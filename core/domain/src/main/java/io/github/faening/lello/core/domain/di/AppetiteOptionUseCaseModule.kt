package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.appetite.DeleteAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.GetAllAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.GetAppetiteOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.SaveAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.UpdateAppetiteOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.UpdateAppetiteOptionUseCase
import io.github.faening.lello.core.model.option.AppetiteOption

@Module
@InstallIn(SingletonComponent::class)
object AppetiteOptionUseCaseModule {

    @Provides
    fun provideGetAllAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = GetAllAppetiteOptionUseCase(repository)

    @Provides
    fun provideGetAppetiteOptionByIdUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = GetAppetiteOptionByIdUseCase(repository)

    @Provides
    fun provideSaveAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = SaveAppetiteOptionUseCase(repository)

    @Provides
    fun provideUpdateAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = UpdateAppetiteOptionUseCase(repository)

    @Provides
    fun provideUpdateAppetiteOptionActiveStatusUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = UpdateAppetiteOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteAppetiteOptionUseCase(
        repository: OptionRepository<AppetiteOption>
    ) = DeleteAppetiteOptionUseCase(repository)
}
