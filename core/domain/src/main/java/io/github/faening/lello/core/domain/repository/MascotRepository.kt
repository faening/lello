package io.github.faening.lello.core.domain.repository

import io.github.faening.lello.core.model.mascot.MascotStatus
import io.github.faening.lello.core.model.mascot.MascotVitalityHistory

interface MascotRepository {
    suspend fun getMascotStatus(): MascotStatus
    suspend fun updateVitality(newVitality: Int, source: String): MascotStatus
    suspend fun getVitalityHistory(): List<MascotVitalityHistory>
}
