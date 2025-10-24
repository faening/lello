package io.github.faening.lello.core.domain.repository

interface ItemResource<T> {
    suspend fun getAllItems(): List<T>
    suspend fun getItemById(id: String): T?
}
