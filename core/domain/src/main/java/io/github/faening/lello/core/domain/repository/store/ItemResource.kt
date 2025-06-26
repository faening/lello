package io.github.faening.lello.core.domain.repository.store

interface ItemResource<T> {
    suspend fun getAllItems(): List<T>
    suspend fun getItemById(id: String): T?
}
