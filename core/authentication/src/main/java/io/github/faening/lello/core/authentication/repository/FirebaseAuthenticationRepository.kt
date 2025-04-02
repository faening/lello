package io.github.faening.lello.core.authentication.repository

import com.google.firebase.auth.FirebaseAuth
import io.github.faening.lello.core.authentication.mapper.toUser
import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import io.github.faening.lello.core.model.authentication.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthenticationRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationRepository {

    override fun isUserAuthenticated(): Flow<Boolean> = flow {
        emit(firebaseAuth.currentUser != null)
    }

    override fun getCurrentUser(): Flow<User?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.toUser())
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult<Unit> = try {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
        AuthResult.Success(Unit)
    } catch (e: Exception) {
        AuthResult.Error(e.message ?: "Falha ao fazer login")
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult<Unit> = try {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        AuthResult.Success(Unit)
    } catch (e: Exception) {
        AuthResult.Error(e.message ?: "Falha ao criar conta")
    }

    override suspend fun sendPasswordResetEmail(
        email: String
    ): AuthResult<Unit> = try {
        firebaseAuth.sendPasswordResetEmail(email).await()
        AuthResult.Success(Unit)
    } catch (e: Exception) {
        AuthResult.Error(e.message ?: "Falha ao enviar email de recuperação")
    }

    override suspend fun signOut(): AuthResult<Unit> = try {
        firebaseAuth.signOut()
        AuthResult.Success(Unit)
    } catch (e: Exception) {
        AuthResult.Error(e.message ?: "Falha ao fazer logout")
    }
}