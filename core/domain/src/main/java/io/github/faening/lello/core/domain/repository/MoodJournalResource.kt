package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface MoodJournalResource<T> {
    /**
     * Obtém todos os registros do diário de humor.
     *
     * @return Flow contendo lista de todos os registros ordenados por data de criação
     */
    fun getAllJournals(): Flow<List<T>>

    /**
     * Obtém um registro do diário de humor pelo ID.
     *
     * @param id ID do registro
     * @return Flow contendo o registro correspondente, ou null se não encontrado
     */
    fun getJournalById(id: Long): Flow<T>?

    /**
     * Obtém registros do diário de humor filtrando por tipo de humor.
     *
     * @param mood Tipo de humor
     * @return Flow contendo lista de registros filtrados ordenados por data
     */
    fun getJournalsByMood(mood: String): Flow<List<T>>
}

/**
 * Contrato para DAOs que gerenciam registros do diário de humor.
 *
 * @param O Tipo do modelo de saída com opções relacionadas
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface MoodJournalDaoContract<O, E> :
    MoodJournalResource<O>,
    BaseWrite<E>

/**
 * Repositório para gerenciar registros do diário de humor.
 *
 * @param M Tipo do modelo de domínio do diário de humor
 */
interface MoodJournalRepository<M> :
    MoodJournalResource<M>,
    BaseWrite<M> {

    /**
     * Insere referências de opções de emoção para um registro do diário.
     *
     * @param moodJournalId ID do registro do diário
     * @param emotionOptionIds Lista de IDs das opções de emoção
     */
    suspend fun linkEmotionOptions(moodJournalId: Long, emotionOptionIds: List<Long>)

    /**
     * Insere referências de opções de clima para um registro do diário.
     *
     * @param moodJournalId ID do registro do diário
     * @param climateOptionIds Lista de IDs das opções de clima
     */
    suspend fun linkClimateOptions(moodJournalId: Long, climateOptionIds: List<Long>)

    /**
     * Insere referências de opções de localização para um registro do diário.
     *
     * @param moodJournalId ID do registro do diário
     * @param locationOptionIds Lista de IDs das opções de localização
     */
    suspend fun linkLocationOptions(moodJournalId: Long, locationOptionIds: List<Long>)

    /**
     * Insere referências de opções sociais para um registro do diário.
     *
     * @param moodJournalId ID do registro do diário
     * @param socialOptionIds Lista de IDs das opções sociais
     */
    suspend fun linkSocialOptions(moodJournalId: Long, socialOptionIds: List<Long>)

    /**
     * Insere referências de opções de saúde para um registro do diário.
     *
     * @param moodJournalId ID do registro do diário
     * @param healthOptionIds Lista de IDs das opções de saúde
     */
    suspend fun linkHealthOptions(moodJournalId: Long, healthOptionIds: List<Long>)
}