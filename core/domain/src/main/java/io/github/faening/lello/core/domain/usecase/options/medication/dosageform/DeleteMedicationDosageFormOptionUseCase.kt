package io.github.faening.lello.core.domain.usecase.options.medication.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import javax.inject.Inject

class DeleteMedicationDosageFormOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageFormOption>
) {
    suspend operator fun invoke(vararg items: MedicationDosageFormOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
