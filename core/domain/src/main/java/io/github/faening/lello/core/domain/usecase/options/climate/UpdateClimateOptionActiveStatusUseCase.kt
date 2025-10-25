package io.github.faening.lello.core.domain.usecase.options.climate

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.ClimateOption
import javax.inject.Inject

class UpdateClimateOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: ClimateOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
