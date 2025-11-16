package io.github.faening.lello.core.domain.repository

interface ItemInventoryResource<T> {
    suspend fun getAllItems(): List<T>
    suspend fun getById(id: Long): T?
    suspend fun getByItemId(itemId: Long): T?
    suspend fun insert(item: T): Long
    suspend fun update(item: T)
}

/**
 * Contrato para DAOs que gerenciam o inventário de itens.
 *
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface ItemInventoryDaoContract<E> : ItemInventoryResource<E>

/**
 * Repositório para gerenciar o inventário de itens.
 *
 * @param M Tipo do modelo de domínio do item do inventário
 */
interface ItemInventoryRepository<M> : ItemInventoryResource<M>