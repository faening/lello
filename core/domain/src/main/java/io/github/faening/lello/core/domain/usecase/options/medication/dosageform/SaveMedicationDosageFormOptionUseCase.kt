package io.github.faening.lello.core.domain.usecase.options.medication.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import javax.inject.Inject

class SaveMedicationDosageFormOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageFormOption>
) {
    suspend operator fun invoke(vararg items: MedicationDosageFormOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
