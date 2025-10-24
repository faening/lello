package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MascotStatusDao
import io.github.faening.lello.core.database.dao.MascotVitalityHistoryDao
import io.github.faening.lello.core.database.model.mascot.toEntity
import io.github.faening.lello.core.database.model.mascot.toModel
import io.github.faening.lello.core.domain.repository.MascotRepository
import io.github.faening.lello.core.model.mascot.MascotStatus
import io.github.faening.lello.core.model.mascot.MascotVitalityHistory
import javax.inject.Inject

class DataMascotRepository @Inject constructor(
    private val statusDao: MascotStatusDao,
    private val historyDao: MascotVitalityHistoryDao
) : MascotRepository {

    override suspend fun getMascotStatus(): MascotStatus {
        return statusDao.getStatus()?.toModel() ?: run {
            val now = System.currentTimeMillis()
            val status = MascotStatus(vitality = 100, lastUpdatedAt = now)
            statusDao.insertOrUpdate(status.toEntity())
            status
        }
    }

    override suspend fun updateVitality(newVitality: Int, source: String): MascotStatus {
        val current = getMascotStatus()
        val delta = newVitality - current.vitality
        val now = System.currentTimeMillis()
        val updated = current.copy(vitality = newVitality, lastUpdatedAt = now)
        statusDao.insertOrUpdate(updated.toEntity())

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
        historyDao.insert(history.toEntity())
        return updated
    }

    override suspend fun getVitalityHistory(): List<MascotVitalityHistory> {
        return historyDao.getHistory().map { it.toModel() }
    }
}