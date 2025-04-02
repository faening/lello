package io.github.faening.lello.core.domain.repository

import io.github.faening.lello.core.model.authentication.AuthResult
import io.github.faening.lello.core.model.authentication.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserAuthenticated(): Flow<Boolean>
    fun getCurrentUser(): Flow<User?>

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult<Unit>
    suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult<Unit>
    suspend fun sendPasswordResetEmail(email: String): AuthResult<Unit>
    suspend fun signOut(): AuthResult<Unit>
}