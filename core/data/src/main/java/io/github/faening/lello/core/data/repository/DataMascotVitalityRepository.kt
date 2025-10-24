package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MascotVitalityHistoryDao
import io.github.faening.lello.core.database.model.mascot.toEntity
import io.github.faening.lello.core.database.model.mascot.toModel
import io.github.faening.lello.core.domain.repository.MascotVitalityRepository
import io.github.faening.lello.core.model.mascot.MascotVitalityHistory
import javax.inject.Inject

class DataMascotVitalityRepository @Inject constructor(
    private val dao: MascotVitalityHistoryDao
) : MascotVitalityRepository<MascotVitalityHistory> {

    override suspend fun insert(history: MascotVitalityHistory) {
        dao.insert(history.toEntity())
    }

    override suspend fun getHistory(): List<MascotVitalityHistory> {
        return dao.getHistory().map { it.toModel() }
    }

    override suspend fun getHistoryBetween(from: Long, to: Long): List<MascotVitalityHistory> {
        return dao.getHistoryBetween(from, to).map { it.toModel() }
    }
}

