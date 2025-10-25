package io.github.faening.lello.core.domain.usecase.options.social

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.SocialOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSocialOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<SocialOption>
) {
    operator fun invoke(id: Long): Flow<SocialOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
