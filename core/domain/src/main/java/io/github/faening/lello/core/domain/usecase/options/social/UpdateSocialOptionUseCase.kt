package io.github.faening.lello.core.domain.usecase.options.social

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.SocialOption
import javax.inject.Inject

class UpdateSocialOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SocialOption>
) {
    suspend operator fun invoke(vararg items: SocialOption) {
        val formattedItems = items.map { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.update(item) }
    }
}
