package io.github.faening.lello.core.domain.usecase.options.medication.dosageunit

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import javax.inject.Inject

class DeleteMedicationDosageUnitOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageUnitOption>
) {
    suspend operator fun invoke(vararg items: MedicationDosageUnitOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
