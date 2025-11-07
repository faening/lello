package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface MealJournalResource<T> {
    /**
     * Obtém todos os registros do diário de alimentação.
     *
     * @return Flow contendo lista de todos os registros ordenados por data de criação
     */
    fun getAllJournals(): Flow<List<T>>

    /**
     * Obtém um registro do diário de alimentação pelo ID.
     *
     * @param id ID do registro
     * @return Flow contendo o registro correspondente, ou null se não encontrado
     */
    fun getJournalById(id: Long): Flow<T>?
}

/**
 * Contrato para DAOs que gerenciam registros do diário de alimentação.
 *
 * @param O Tipo do modelo de saída com opções relacionadas
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface MealJournalDaoContract<O, E> :
    MealJournalResource<O>,
    BaseWrite<E>

/**
 * Repositório para gerenciar registros do diário de alimentação.
 *
 * @param M Tipo do modelo de domínio do diário de alimentação
 */
interface MealJournalRepository<M> :
    MealJournalResource<M>,
    BaseWrite<M> {

    /**
     * Insere referências de opções de refeição para um registro do diário.
     *
     * @param mealJournalId ID do registro do diário
     * @param mealOptionIds Lista de IDs das opções de refeição
     */
    suspend fun linkMealOptions(mealJournalId: Long, mealOptionIds: List<Long>)

    /**
     * Insere referências de opções de apetite para um registro do diário.
     *
     * @param mealJournalId ID do registro do diário
     * @param appetiteOptionIds Lista de IDs das opções de apetite
     */
    suspend fun linkAppetiteOptions(mealJournalId: Long, appetiteOptionIds: List<Long>)

    /**
     * Insere referências de opções de alimento para um registro do diário.
     *
     * @param mealJournalId ID do registro do diário
     * @param foodOptionIds Lista de IDs das opções de alimento
     */
    suspend fun linkFoodOptions(mealJournalId: Long, foodOptionIds: List<Long>)

    /**
     * Insere referências de opções de porção para um registro do diário.
     *
     * @param mealJournalId ID do registro do diário
     * @param portionOptionIds Lista de IDs das opções de porção
     */
    suspend fun linkPortionOptions(mealJournalId: Long, portionOptionIds: List<Long>)

    /**
     * Insere referências de opções de localização para um registro do diário.
     *
     * @param mealJournalId ID do registro do diário
     * @param locationOptionIds Lista de IDs das opções de localização
     */
    suspend fun linkLocationOptions(mealJournalId: Long, locationOptionIds: List<Long>)

    /**
     * Insere referências de opções sociais para um registro do diário.
     *
     * @param mealJournalId ID do registro do diário
     * @param socialOptionIds Lista de IDs das opções sociais
     */
    suspend fun linkSocialOptions(mealJournalId: Long, socialOptionIds: List<Long>)
}