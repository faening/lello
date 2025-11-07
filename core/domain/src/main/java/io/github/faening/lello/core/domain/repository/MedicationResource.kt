package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface MedicationReadResource<T> {
    fun getAllMedications(): Flow<List<T>>
    fun getMedicationById(id: Long): T?
}

/**
 * Contrato para DAOs que gerenciam registros de medicamentos.
 *
 * @param O Tipo do modelo de saída com opções relacionadas
 * @param E Tipo da entidade de banco de dados para escrita
 * @param S Tipo da entidade de dosagem de medicamento
 */
interface MedicationDaoContract<O, E, S> :
    MedicationReadResource<O> {

    suspend fun insert(item: E): Long
    suspend fun insertDosages(dosages: List<S>)
    suspend fun update(item: E)
    suspend fun disable(medicationId: Long)
    suspend fun deleteDosagesForMedicationId(medicationId: Long)
}

/**
 * Repositório para gerenciar registros de medicamentos.
 *
 * @param M Tipo do modelo de domínio do medicamento
 */
interface MedicationRepository<M> :
    MedicationReadResource<M> {

    suspend fun insert(item: M): Long
    suspend fun update(item: M)
    suspend fun disable(id: Long)
}