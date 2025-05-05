package io.github.faening.lello.core.domain.usecase.climate_option

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.model.journal.ClimateOption
import javax.inject.Inject

class ClimateOptionInsertUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {
    suspend fun invoke(item: ClimateOption) {
        if (item.description.isBlank()) {
            throw IllegalArgumentException("A descrição do clima não pode estar vazia.")
        }

        item.copy(description = item.description.capitalizeFirst())

        repository.insert(item)
    }
}