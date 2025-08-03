package io.github.faening.lello.core.domain.usecase.mascot

import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.model.mascot.MascotStatus
import javax.inject.Inject

class GetMascotStatusUseCase @Inject constructor(
    private val repository: MascotRepository
) {
    suspend operator fun invoke(): MascotStatus {
        return repository.getMascotStatus()
    }
}
