package io.github.faening.lello.core.domain.usecase.options.appetite

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.AppetiteOption
import javax.inject.Inject

class UpdateAppetiteOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<AppetiteOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: AppetiteOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}