package io.github.faening.lello.core.domain.repository.store

import io.github.faening.lello.core.model.store.Item

interface ItemResource {
    suspend fun getAllItems(): List<Item>
    suspend fun getItemById(id: String): Item?
}
