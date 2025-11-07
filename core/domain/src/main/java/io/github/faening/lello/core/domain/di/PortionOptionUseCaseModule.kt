package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.portion.DeletePortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.GetAllPortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.GetPortionOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.SavePortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.UpdatePortionOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.UpdatePortionOptionUseCase
import io.github.faening.lello.core.model.option.PortionOption

@Module
@InstallIn(SingletonComponent::class)
object PortionOptionUseCaseModule {

    @Provides
    fun provideGetAllPortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = GetAllPortionOptionUseCase(repository)

    @Provides
    fun provideGetPortionOptionByIdUseCase(
        repository: OptionRepository<PortionOption>
    ) = GetPortionOptionByIdUseCase(repository)

    @Provides
    fun provideSavePortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = SavePortionOptionUseCase(repository)

    @Provides
    fun provideUpdatePortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = UpdatePortionOptionUseCase(repository)

    @Provides
    fun provideUpdatePortionOptionActiveStatusUseCase(
        repository: OptionRepository<PortionOption>
    ) = UpdatePortionOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeletePortionOptionUseCase(
        repository: OptionRepository<PortionOption>
    ) = DeletePortionOptionUseCase(repository)
}
