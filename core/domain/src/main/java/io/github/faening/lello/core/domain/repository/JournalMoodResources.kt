package io.github.faening.lello.core.domain.repository

interface JournalMoodResources<T> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: Int): T?
    suspend fun insert(entry: T) : Long
    suspend fun delete(id: Int)
}