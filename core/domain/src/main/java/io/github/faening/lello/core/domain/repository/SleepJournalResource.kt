package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface SleepJournalResource<T> {
    /**
     * Obtém todos os registros do diário de sono.
     *
     * @return Flow contendo lista de todos os registros ordenados por data de criação
     */
    fun getAllJournals(): Flow<List<T>>

    /**
     * Obtém um registro do diário de sono pelo ID.
     *
     * @param id ID do registro
     * @return Flow contendo o registro correspondente, ou null se não encontrado
     */
    fun getJournalById(id: Long): Flow<T>?
}

/**
 * Contrato para DAOs que gerenciam registros do diário de sono.
 *
 * @param O Tipo do modelo de saída com opções relacionadas
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface SleepJournalDaoContract<O, E> :
    SleepJournalResource<O>,
    BaseJournalWrite<E>

/**
 * Repositório para gerenciar registros do diário de sono.
 *
 * @param M Tipo do modelo de domínio do diário de sono
 */
interface SleepJournalRepository<M> :
    SleepJournalResource<M>,
    BaseJournalWrite<M> {

    /**
     * Insere referências de opções de sensação de sono para um registro do diário.
     *
     * @param sleepJournalId ID do registro do diário
     * @param sleepSensationOptionIds Lista de IDs das opções de sensação
     */
    suspend fun linkSleepSensationOptions(sleepJournalId: Long, sleepSensationOptionIds: List<Long>)

    /**
     * Insere referências de opções de qualidade de sono para um registro do diário.
     *
     * @param sleepJournalId ID do registro do diário
     * @param sleepQualityOptionIds Lista de IDs das opções de qualidade
     */
    suspend fun linkSleepQualityOptions(sleepJournalId: Long, sleepQualityOptionIds: List<Long>)

    /**
     * Insere referências de opções de atividade de sono para um registro do diário.
     *
     * @param sleepJournalId ID do registro do diário
     * @param sleepActivityOptionIds Lista de IDs das opções de atividade
     */
    suspend fun linkSleepActivityOptions(sleepJournalId: Long, sleepActivityOptionIds: List<Long>)

    /**
     * Insere referências de opções de localização para um registro do diário.
     *
     * @param sleepJournalId ID do registro do diário
     * @param locationOptionIds Lista de IDs das opções de localização
     */
    suspend fun linkLocationOptions(sleepJournalId: Long, locationOptionIds: List<Long>)
}