package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.DeleteMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetAllMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetMedicationDosageFormOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.SaveMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.UpdateMedicationDosageFormOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.UpdateMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.model.option.MedicationDosageFormOption

@Module
@InstallIn(SingletonComponent::class)
object MedicationDosageFormOptionUseCaseModule {

    @Provides
    fun provideGetAllMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = GetAllMedicationDosageFormOptionUseCase(repository)

    @Provides
    fun provideGetMedicationDosageFormOptionByIdUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = GetMedicationDosageFormOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = SaveMedicationDosageFormOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = UpdateMedicationDosageFormOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationDosageFormOptionActiveStatusUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = UpdateMedicationDosageFormOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMedicationDosageFormOptionUseCase(
        repository: OptionRepository<MedicationDosageFormOption>
    ) = DeleteMedicationDosageFormOptionUseCase(repository)
}
