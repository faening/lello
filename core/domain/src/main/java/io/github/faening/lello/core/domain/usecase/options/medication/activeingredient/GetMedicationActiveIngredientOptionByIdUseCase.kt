package io.github.faening.lello.core.domain.usecase.options.medication.activeingredient

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicationActiveIngredientOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationActiveIngredientOption>
) {
    operator fun invoke(id: Long): Flow<MedicationActiveIngredientOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
