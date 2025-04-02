package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult<Unit> {
        if (email.isBlank() || password.isBlank()) {
            return AuthResult.Error("Email e senha não podem estar vazios")
        }

        if (password.length < 6) {
            return AuthResult.Error("A senha deve ter pelo menos 6 caracteres")
        }

        return repository.signUpWithEmailAndPassword(email, password)
    }
}