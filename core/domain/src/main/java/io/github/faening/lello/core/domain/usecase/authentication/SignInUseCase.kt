package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult<Unit> {
        if (email.isBlank() || password.isBlank()) {
            return AuthResult.Error("Email e senha não podem estar vazios")
        }

        return repository.signInWithEmailAndPassword(email, password)
    }
}