package io.github.faening.lello.core.domain.usecase.options.sleep.quality

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.SleepQualityOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSleepQualityOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<SleepQualityOption>
) {
    fun invoke(id: Long): Flow<SleepQualityOption>? {
        id.validateId()
        return repository.getById(id)
    }
}