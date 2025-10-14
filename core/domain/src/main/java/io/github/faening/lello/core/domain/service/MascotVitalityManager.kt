package io.github.faening.lello.core.domain.service

import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.mascot.UpdateMascotVitalityUseCase
import io.github.faening.lello.core.model.mascot.MascotStatus
import javax.inject.Inject

class MascotVitalityManager @Inject constructor(
    private val getMascotStatusUseCase: GetMascotStatusUseCase,
    private val updateMascotVitalityUseCase: UpdateMascotVitalityUseCase
) {
    companion object {
        private const val MAX_VITALITY = 100
        private const val MIN_VITALITY = 0
        private const val DECAY_DURATION_MS = 72L * 60L * 60L * 1000L // 72 hours
    }

    suspend fun getCurrentVitality(): MascotStatus {
        return syncVitalityDecay()
    }

    suspend fun feedMascot(amount: Int): MascotStatus {
        val status = getMascotStatusUseCase()
        val newVitality = (status.vitality + amount).coerceAtMost(MAX_VITALITY)
        return updateMascotVitalityUseCase(newVitality, source = "food")
    }

    suspend fun syncVitalityDecay(): MascotStatus {
        val status = getMascotStatusUseCase()
        val now = System.currentTimeMillis()
        val elapsed = now - status.lastUpdatedAt
        val decay = (elapsed.toDouble() / DECAY_DURATION_MS * MAX_VITALITY).toInt()
        val newVitality = (status.vitality - decay).coerceIn(MIN_VITALITY, MAX_VITALITY)
        return if (newVitality != status.vitality) {
            updateMascotVitalityUseCase(newVitality, source = "decay")
        } else {
            status
        }
    }
}
