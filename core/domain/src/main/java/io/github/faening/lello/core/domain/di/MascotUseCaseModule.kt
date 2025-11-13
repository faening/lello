package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotVitalityHistoryUseCase
import io.github.faening.lello.core.domain.usecase.mascot.UpdateMascotVitalityUseCase

@Module
@InstallIn(SingletonComponent::class)
object MascotUseCaseModule {

    @Provides
    fun provideGetMascotStatusUseCase(
        repository: MascotRepository
    ) = GetMascotStatusUseCase(repository)

    @Provides
    fun provideGetMascotVitalityHistoryUseCase(
        repository: MascotRepository
    ) = GetMascotVitalityHistoryUseCase(repository)

    @Provides
    fun provideUpdateMascotVitalityUseCase(
        repository: MascotRepository
    ) = UpdateMascotVitalityUseCase(repository)
}
