package io.github.faening.lello.core.domain.usecase.options.medication.activeingredient

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMedicationActiveIngredientOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationActiveIngredientOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<MedicationActiveIngredientOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
