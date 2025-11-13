package io.github.faening.lello.core.domain.repository

/**
 * Contrato base para operações de escrita em diários.
 *
 * @param T Tipo do item a ser manipulado
 */
interface BaseJournalWrite<T> {
    /**
     * Insere um novo registro.
     *
     * @param item Registro a ser inserido
     * @return ID gerado para o novo registro
     */
    suspend fun insert(item: T): Long

    /**
     * Insere múltiplos registros em uma única operação.
     *
     * @param item Lista de registros a serem inseridos
     * @return Lista com os IDs gerados para cada registro
     */
    suspend fun insert(items: List<T>): List<Long>
}

/**
 * Contrato base para operações de escrita genéricas.
 *
 * @param T Tipo do item a ser manipulado
 */
interface BaseWrite<T> {
    /**
     * Insere um novo item.
     *
     * @param item Item a ser inserido
     * @return ID gerado para o novo item
     */
    suspend fun insert(item: T): Long

    /**
     * Atualiza um item existente.
     *
     * @param item Item a ser atualizado
     */
    suspend fun update(item: T)

    /**
     * Desabilita um item pelo seu ID.
     *
     * @param id ID do item a ser desabilitado
     */
    suspend fun disable(id: Long)

    /**
     * Deleta um item.
     *
     * @param item Item a ser deletado
     */
    suspend fun delete(item: T)
}