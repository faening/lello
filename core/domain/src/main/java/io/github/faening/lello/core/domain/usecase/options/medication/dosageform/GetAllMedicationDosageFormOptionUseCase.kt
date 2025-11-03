package io.github.faening.lello.core.domain.usecase.options.medication.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMedicationDosageFormOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageFormOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<MedicationDosageFormOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
