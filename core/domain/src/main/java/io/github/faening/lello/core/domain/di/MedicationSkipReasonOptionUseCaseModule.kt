package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.DeleteMedicationSkipReasonOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.GetAllMedicationSkipReasonOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.GetMedicationSkipReasonOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.SaveMedicationSkipReasonOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.UpdateMedicationSkipReasonOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.UpdateMedicationSkipReasonOptionUseCase
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption

@Module
@InstallIn(SingletonComponent::class)
object MedicationSkipReasonOptionUseCaseModule {

    @Provides
    fun provideGetAllMedicationSkipReasonOptionUseCase(
        repository: OptionRepository<MedicationSkipReasonOption>
    ) = GetAllMedicationSkipReasonOptionUseCase(repository)

    @Provides
    fun provideGetMedicationSkipReasonOptionByIdUseCase(
        repository: OptionRepository<MedicationSkipReasonOption>
    ) = GetMedicationSkipReasonOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationSkipReasonOptionUseCase(
        repository: OptionRepository<MedicationSkipReasonOption>
    ) = SaveMedicationSkipReasonOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationSkipReasonOptionUseCase(
        repository: OptionRepository<MedicationSkipReasonOption>
    ) = UpdateMedicationSkipReasonOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationSkipReasonOptionActiveStatusUseCase(
        repository: OptionRepository<MedicationSkipReasonOption>
    ) = UpdateMedicationSkipReasonOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMedicationSkipReasonOptionUseCase(
        repository: OptionRepository<MedicationSkipReasonOption>
    ) = DeleteMedicationSkipReasonOptionUseCase(repository)
}
