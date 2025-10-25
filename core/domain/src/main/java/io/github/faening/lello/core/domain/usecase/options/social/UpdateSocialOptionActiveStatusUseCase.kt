package io.github.faening.lello.core.domain.usecase.options.social

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.SocialOption
import javax.inject.Inject

class UpdateSocialOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<SocialOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: SocialOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
