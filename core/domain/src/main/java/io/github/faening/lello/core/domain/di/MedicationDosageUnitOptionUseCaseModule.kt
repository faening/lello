package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.DeleteMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.GetAllMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.GetMedicationDosageUnitOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.SaveMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.UpdateMedicationDosageUnitOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.UpdateMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption

@Module
@InstallIn(SingletonComponent::class)
object MedicationDosageUnitOptionUseCaseModule {

    @Provides
    fun provideGetAllMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = GetAllMedicationDosageUnitOptionUseCase(repository)

    @Provides
    fun provideGetMedicationDosageUnitOptionByIdUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = GetMedicationDosageUnitOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = SaveMedicationDosageUnitOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = UpdateMedicationDosageUnitOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageUnitOptionActiveStatusUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = UpdateMedicationDosageUnitOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMedicationDosageUnitOptionUseCase(
        repository: OptionRepository<MedicationDosageUnitOption>
    ) = DeleteMedicationDosageUnitOptionUseCase(repository)
}
