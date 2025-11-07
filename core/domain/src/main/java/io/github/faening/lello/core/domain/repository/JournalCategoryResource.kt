package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface JournalCategoryResource<T> {
    /**
     * Obtém todos os registros de categorias do diário.
     *
     * @param useBlockedFilter Indica se o filtro de bloqueado deve ser aplicado
     * @param isBlocked Valor do filtro de bloqueado
     * @param useActiveFilter Indica se o filtro de ativo deve ser aplicado
     * @param isActive Valor do filtro de ativo
     *
     * @return Flow contendo lista de todas as categorias ordenadas por nome
     */
    fun getAllJournalCategories(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<T>>

    /**
     * Obtém uma categoria do diário pelo ID.
     *
     * @param id ID da categoria
     * @return Flow contendo a categoria correspondente, ou null se não encontrada
     */
    fun getJournalCategoryById(id: Long): Flow<T>?
}

/**
 * Contrato para DAOs que gerenciam categorias do diário.
 *
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface JournalCategoryDaoContract<E> : JournalCategoryResource<E>

/**
 * Repositório para gerenciar categorias do diário.
 *
 * @param M Tipo do modelo de domínio da categoria do diário
 */
interface JournalCategoryRepository<M> : JournalCategoryResource<M>