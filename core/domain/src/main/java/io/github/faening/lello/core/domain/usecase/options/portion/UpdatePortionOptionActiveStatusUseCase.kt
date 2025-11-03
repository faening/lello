package io.github.faening.lello.core.domain.usecase.options.portion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.PortionOption
import javax.inject.Inject

class UpdatePortionOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<PortionOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: PortionOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
