package io.github.faening.lello.core.domain.usecase.options.location

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.LocationOption
import javax.inject.Inject

class SaveLocationOptionUseCase @Inject constructor(
    private val repository: OptionRepository<LocationOption>
) {
    suspend operator fun invoke(vararg items: LocationOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
