package io.github.faening.lello.core.domain.usecase.climate_option

import io.github.faening.lello.core.domain.repository.OptionResources
import io.github.faening.lello.core.model.journal.ClimateOption
import javax.inject.Inject

class ClimateOptionDeleteUseCase @Inject constructor(
    private val repository: OptionResources<ClimateOption>
) {
    suspend fun invoke(item: ClimateOption) {
        if (item.id == null) {
            throw IllegalArgumentException("O ID não pode ser nulo")
        }

        repository.delete(item)
    }
}