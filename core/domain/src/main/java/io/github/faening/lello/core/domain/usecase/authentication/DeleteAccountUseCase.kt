package io.github.faening.lello.core.domain.usecase.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    /**
     * Deleta a conta do usuário atualmente autenticado.
     * - true em caso de sucesso
     * - false se usuário for nulo ou erro genérico
     * Lança FirebaseAuthRecentLoginRequiredException se precisar reautenticar.
     */
    suspend operator fun invoke(): Boolean {
        val user = firebaseAuth.currentUser ?: return false
        return try {
            user.delete().await()
            true
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            throw e
        } catch (e: Exception) {
            false
        }
    }
}