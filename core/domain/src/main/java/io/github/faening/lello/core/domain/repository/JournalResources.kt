package io.github.faening.lello.core.domain.repository

interface JournalResources<T> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: Long): T?
    suspend fun insert(entry: T) : Long
    suspend fun delete(id: T)
}