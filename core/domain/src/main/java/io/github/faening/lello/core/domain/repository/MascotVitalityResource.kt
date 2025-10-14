package io.github.faening.lello.core.domain.repository

/**
 * Defines access operations for mascot vitality history.
 */
interface MascotVitalityResource<T> {
    suspend fun insert(history: T)
    suspend fun getHistory(): List<T>
    suspend fun getHistoryBetween(from: Long, to: Long): List<T>
}

