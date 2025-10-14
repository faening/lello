package io.github.faening.lello.core.domain.usecase.mascot

import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.model.mascot.MascotVitalityHistory
import javax.inject.Inject

class GetMascotVitalityHistoryUseCase @Inject constructor(
    private val repository: MascotRepository
) {
    suspend operator fun invoke(): List<MascotVitalityHistory> {
        return repository.getVitalityHistory()
    }
}
