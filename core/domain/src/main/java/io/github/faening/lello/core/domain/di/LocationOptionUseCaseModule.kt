package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.location.DeleteLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetAllLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetLocationOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.location.SaveLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.UpdateLocationOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.location.UpdateLocationOptionUseCase
import io.github.faening.lello.core.model.option.LocationOption

@Module
@InstallIn(SingletonComponent::class)
object LocationOptionUseCaseModule {

    @Provides
    fun provideGetAllLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = GetAllLocationOptionUseCase(repository)

    @Provides
    fun provideGetLocationOptionByIdUseCase(
        repository: OptionRepository<LocationOption>
    ) = GetLocationOptionByIdUseCase(repository)

    @Provides
    fun provideSaveLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = SaveLocationOptionUseCase(repository)

    @Provides
    fun provideUpdateLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = UpdateLocationOptionUseCase(repository)

    @Provides
    fun provideUpdateLocationOptionActiveStatusUseCase(
        repository: OptionRepository<LocationOption>
    ) = UpdateLocationOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteLocationOptionUseCase(
        repository: OptionRepository<LocationOption>
    ) = DeleteLocationOptionUseCase(repository)
}
