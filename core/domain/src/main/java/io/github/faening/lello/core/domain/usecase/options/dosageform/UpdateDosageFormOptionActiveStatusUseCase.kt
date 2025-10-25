package io.github.faening.lello.core.domain.usecase.options.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.DosageFormOption
import javax.inject.Inject

class UpdateDosageFormOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<DosageFormOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: DosageFormOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
