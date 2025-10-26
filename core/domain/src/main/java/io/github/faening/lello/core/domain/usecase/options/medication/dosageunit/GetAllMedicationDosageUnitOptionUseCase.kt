package io.github.faening.lello.core.domain.usecase.options.medication.dosageunit

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMedicationDosageUnitOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageUnitOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<MedicationDosageUnitOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
