package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface RewardBalanceRepository<T> {
    fun observeBalance(): Flow<T>
    suspend fun getBalance(): T?
    suspend fun insertOrUpdate(balance: T)
    suspend fun clearBalance()
}