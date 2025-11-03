package io.github.faening.lello.core.domain.usecase.options.climate

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.ClimateOption
import javax.inject.Inject

class SaveClimateOptionUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {
    suspend operator fun invoke(vararg items: ClimateOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
