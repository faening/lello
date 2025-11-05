package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface MedicationJournalResource<T> {
    /**
     * Obtém todos os registros do diário de medicamentos.
     *
     * @return Flow contendo lista de todos os registros ordenados por data de criação
     */
    fun getAllJournals(): Flow<List<T>>

    /**
     * Obtém registros do diário de medicamentos em um intervalo de tempo específico (um dia).
     *
     * @param dayStart Timestamp do início do dia (00:00)
     * @param dayEnd Timestamp do fim do dia (23:59)
     * @return Flow contendo lista de registros do dia ordenados por horário agendado
     */
    fun getJournalsByDay(dayStart: Long, dayEnd: Long): Flow<List<T>>

    /**
     * Obtém registros do diário filtrando por status de ingestão.
     *
     * @param taken `true` para medicamentos tomados, `false` para não tomados
     * @return Flow contendo lista de registros filtrados ordenados por horário
     */
    fun getJournalsByTakenStatus(taken: Boolean): Flow<List<T>>

    /**
     * Obtém todos os registros de um medicamento específico.
     *
     * @param medicationId ID do medicamento
     * @return Flow contendo lista de registros do medicamento ordenados por data
     */
    fun getJournalsByMedication(medicationId: Long): Flow<List<T>>
}

/**
 * Contrato para DAOs que gerenciam registros do diário de medicamentos.
 *
 * @param O Tipo do modelo de saída com opções relacionadas
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface MedicationJournalDaoContract<O, E> :
    MedicationJournalResource<O>,
    BaseWrite<E>

/**
 * Repositório para gerenciar registros do diário de medicamentos.
 *
 * @param M Tipo do modelo de domínio do diário de medicamentos
 */
interface MedicationJournalRepository<M> :
    MedicationJournalResource<M>,
    BaseWrite<M>