package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.RewardHistoryDao
import io.github.faening.lello.core.database.model.reward.toEntity
import io.github.faening.lello.core.database.model.reward.toModel
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class RewardHistoryRepository @Inject constructor(
    private val dao: RewardHistoryDao
) : RewardHistoryRepository<RewardHistory> {

    override fun observeHistory(): Flow<List<RewardHistory>> {
        return dao
            .observeHistory()
            .map { list -> list.map { it.toModel() } }
    }

    override suspend fun getHistory(): List<RewardHistory> {
        return dao
            .getHistory()
            .map { it.toModel() }
    }

    override suspend fun getHistoryByOrigin(origin: RewardOrigin): List<RewardHistory> {
        return dao
            .getHistoryByOrigin(origin)
            .map { it.toModel() }
    }

    override suspend fun insert(history: RewardHistory) {
        val entity = history.toEntity()
        dao.insert(entity)
    }

    override suspend fun clearHistory() {
        dao.clearHistory()
    }
}