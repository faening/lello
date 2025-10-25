package io.github.faening.lello.core.domain.usecase.options.climate

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.ClimateOption
import javax.inject.Inject

class DeleteClimateOptionUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {
    suspend operator fun invoke(vararg items: ClimateOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
