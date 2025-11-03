package io.github.faening.lello.core.domain.usecase.options.appetite

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.AppetiteOption
import javax.inject.Inject

class DeleteAppetiteOptionUseCase @Inject constructor(
    private val repository: OptionRepository<AppetiteOption>
) {
    suspend operator fun invoke(vararg items: AppetiteOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}