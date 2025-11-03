package io.github.faening.lello.core.domain.usecase.options.portion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.PortionOption
import javax.inject.Inject

class DeletePortionOptionUseCase @Inject constructor(
    private val repository: OptionRepository<PortionOption>
) {
    suspend operator fun invoke(vararg items: PortionOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
