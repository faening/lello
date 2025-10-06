package io.github.faening.lello.core.domain.repository

interface UserRepository {
    suspend fun saveUserEmail(email: String)
    suspend fun getUserEmail(): String?
    suspend fun setBiometricPreference(enabled: Boolean)
    suspend fun getBiometricPreference(): Boolean
}