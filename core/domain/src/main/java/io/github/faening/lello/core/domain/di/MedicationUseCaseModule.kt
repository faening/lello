package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.domain.usecase.medication.DisableMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetAllMedicationsUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetMedicationByIdUseCase
import io.github.faening.lello.core.domain.usecase.medication.SaveMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.UpdateMedicationUseCase
import io.github.faening.lello.core.model.medication.Medication

@Module
@InstallIn(SingletonComponent::class)
object MedicationUseCaseModule {

    @Provides
    fun provideDisableMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = DisableMedicationUseCase(repository)

    @Provides
    fun provideGetAllMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = GetAllMedicationsUseCase(repository)

    @Provides
    fun provideGetMedicationByIdUseCase(
        repository: MedicationRepository<Medication>
    ) = GetMedicationByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = SaveMedicationUseCase(repository)

    @Provides
    fun provideUpdateMedicationUseCase(
        repository: MedicationRepository<Medication>
    ) = UpdateMedicationUseCase(repository)
}
