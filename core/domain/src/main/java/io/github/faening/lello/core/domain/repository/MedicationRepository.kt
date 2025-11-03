package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface MedicationRepository<T> {
    fun getAll(): Flow<List<T>>
    suspend fun getById(id: Long): T?
    suspend fun insert(entry: T): Long
    suspend fun update(entry: T)
    suspend fun disable(id: Long)
}