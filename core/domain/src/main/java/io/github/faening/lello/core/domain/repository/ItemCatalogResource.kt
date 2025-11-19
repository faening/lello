package io.github.faening.lello.core.domain.repository

interface ItemCatalogResource<T> {
    suspend fun getAllItems(): List<T>
    suspend fun getItemById(id: Long): T?
}

/**
 * Contrato para DAOs que gerenciam categorias de itens.
 *
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface ItemCategoryDaoContract<E> : ItemCatalogResource<E>

/**
 * Repositório para gerenciar categorias de itens.
 *
 * @param M Tipo do modelo de domínio da categoria do item
 * @param T Tipo do critério de categorização dos itens
 */
interface ItemCategoryRepository<M, T> : ItemCatalogResource<M> {
    suspend fun getItemsByType(type: T): List<M>
}