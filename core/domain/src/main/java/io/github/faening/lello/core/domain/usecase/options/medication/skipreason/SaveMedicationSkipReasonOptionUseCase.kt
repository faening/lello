package io.github.faening.lello.core.domain.usecase.options.medication.skipreason

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import javax.inject.Inject

class SaveMedicationSkipReasonOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationSkipReasonOption>
) {
    suspend operator fun invoke(vararg items: MedicationSkipReasonOption) {
        val unitattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        unitattedItems.forEach { item -> repository.insert(item) }
    }
}
