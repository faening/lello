package io.github.faening.lello.core.domain.usecase.options.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.DosageFormOption
import javax.inject.Inject

class SaveDosageFormOptionUseCase @Inject constructor(
    private val repository: OptionRepository<DosageFormOption>
) {
    suspend operator fun invoke(vararg items: DosageFormOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
