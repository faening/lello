package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.domain.repository.MascotStatusResource
import io.github.faening.lello.core.domain.repository.MascotVitalityResource
import io.github.faening.lello.core.model.mascot.MascotStatus
import io.github.faening.lello.core.model.mascot.MascotVitalityHistory
import javax.inject.Inject

class MascotRepositoryImpl @Inject constructor(
    private val statusResource: MascotStatusResource<MascotStatus>,
    private val vitalityResource: MascotVitalityResource<MascotVitalityHistory>
) : MascotRepository {

    override suspend fun getMascotStatus(): MascotStatus {
        return statusResource.getStatus() ?: run {
            val now = System.currentTimeMillis()
            val status = MascotStatus(vitality = 100, lastUpdatedAt = now)
            statusResource.insertOrUpdate(status)
            status
        }
    }

    override suspend fun updateVitality(newVitality: Int, source: String): MascotStatus {
        val current = getMascotStatus()
        val delta = newVitality - current.vitality
        val now = System.currentTimeMillis()
        val updated = current.copy(vitality = newVitality, lastUpdatedAt = now)
        statusResource.insertOrUpdate(updated)

        val changeType = when {
            delta > 0 -> "increase"
            delta < 0 -> "decrease"
            else -> "set"
        }
        val history = MascotVitalityHistory(
            changeType = changeType,
            value = newVitality,
            delta = delta,
            source = source,
            createdAt = now
        )
        vitalityResource.insert(history)
        return updated
    }

    override suspend fun getVitalityHistory(): List<MascotVitalityHistory> {
        return vitalityResource.getHistory()
    }
}
