package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.DeleteMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetAllMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetMedicationActiveIngredientOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.SaveMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.UpdateMedicationActiveIngredientOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.UpdateMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption

@Module
@InstallIn(SingletonComponent::class)
object MedicationActiveIngredientOptionUseCaseModule {

    @Provides
    fun provideGetAllMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = GetAllMedicationActiveIngredientOptionUseCase(repository)

    @Provides
    fun provideGetMedicationActiveIngredientOptionByIdUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = GetMedicationActiveIngredientOptionByIdUseCase(repository)

    @Provides
    fun provideSaveMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = SaveMedicationActiveIngredientOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = UpdateMedicationActiveIngredientOptionUseCase(repository)

    @Provides
    fun provideUpdateMedicationActiveIngredientOptionActiveStatusUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = UpdateMedicationActiveIngredientOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteMedicationActiveIngredientOptionUseCase(
        repository: OptionRepository<MedicationActiveIngredientOption>
    ) = DeleteMedicationActiveIngredientOptionUseCase(repository)
}
