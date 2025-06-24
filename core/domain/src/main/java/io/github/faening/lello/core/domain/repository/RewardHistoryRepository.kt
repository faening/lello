package io.github.faening.lello.core.domain.repository

import io.github.faening.lello.core.model.reward.RewardOrigin
import kotlinx.coroutines.flow.Flow

interface RewardHistoryRepository<T> {
    fun observeHistory(): Flow<List<T>>
    suspend fun getHistory(): List<T>
    suspend fun getHistoryByOrigin(origin: RewardOrigin): List<T>
    suspend fun insert(history: T)
    suspend fun clearHistory()
}