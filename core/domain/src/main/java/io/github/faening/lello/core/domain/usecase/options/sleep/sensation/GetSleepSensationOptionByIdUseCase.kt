package io.github.faening.lello.core.domain.usecase.options.sleep.sensation

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.SleepSensationOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSleepSensationOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<SleepSensationOption>
) {
    fun invoke(id: Long): Flow<SleepSensationOption>? {
        id.validateId()
        return repository.getById(id)
    }
}