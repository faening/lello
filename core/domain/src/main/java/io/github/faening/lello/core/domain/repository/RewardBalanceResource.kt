package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface RewardBalanceResource<T> {
    suspend fun getBalance(): T?
    suspend fun insertOrUpdate(balance: T)
    suspend fun clearBalance()
}

/**
 * Contrato para DAOs que gerenciam registros de saldo de recompensas.
 *
 * @param E Tipo da entidade de banco de dados para escrita
 */
interface RewardBalanceDaoContract<E> : RewardBalanceResource<E> {
    fun observeBalance(): Flow<E?>
}

/**
 * Repositório para gerenciar registros de saldo de recompensas.
 *
 * @param M Tipo do modelo de domínio do saldo de recompensas
 */
interface RewardBalanceRepository<M> : RewardBalanceResource<M> {
    fun observeBalance(): Flow<M>
}