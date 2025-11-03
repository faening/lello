package io.github.faening.lello.core.domain.usecase.options.medication.skipreason

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMedicationSkipReasonOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationSkipReasonOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<MedicationSkipReasonOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
