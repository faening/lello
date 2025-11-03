package io.github.faening.lello.core.domain.usecase.options.appetite

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.AppetiteOption
import javax.inject.Inject

class SaveAppetiteOptionUseCase @Inject constructor(
    private val repository: OptionRepository<AppetiteOption>
) {
    suspend operator fun invoke(vararg items: AppetiteOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}