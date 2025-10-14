package io.github.faening.lello.core.domain.usecase.authentication

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import io.github.faening.lello.core.model.authentication.AuthResult
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ValidatePasswordUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val getUserEmailUseCase: GetUserEmailUseCase
) {
    suspend operator fun invoke(password: String): AuthResult<Unit> {
        val currentUser = firebaseAuth.currentUser
        val email = getUserEmailUseCase()

        if (currentUser == null || email.isNullOrEmpty()) {
            return AuthResult.Error("Usuário não autenticado")
        }

        return suspendCoroutine { continuation ->
            val credential = EmailAuthProvider.getCredential(email, password)

            currentUser.reauthenticate(credential)
                .addOnSuccessListener {
                    continuation.resume(AuthResult.Success(Unit))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(AuthResult.Error(exception.message ?: "Senha incorreta"))
                }
        }
    }
}
