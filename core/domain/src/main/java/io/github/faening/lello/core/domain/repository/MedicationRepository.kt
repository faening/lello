package io.github.faening.lello.core.domain.repository

interface MedicationRepository<T> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: Long): T?
    suspend fun insert(entry: T): Long
    suspend fun update(entry: T)
    suspend fun disable(id: Long)
}