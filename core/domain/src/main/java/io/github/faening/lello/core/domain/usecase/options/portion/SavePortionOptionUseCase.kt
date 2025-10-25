package io.github.faening.lello.core.domain.usecase.options.portion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.PortionOption
import javax.inject.Inject

class SavePortionOptionUseCase @Inject constructor(
    private val repository: OptionRepository<PortionOption>
) {
    suspend operator fun invoke(vararg items: PortionOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
