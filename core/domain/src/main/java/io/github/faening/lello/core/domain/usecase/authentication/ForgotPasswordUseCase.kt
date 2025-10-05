package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String): AuthResult<Unit> {
        if (email.isBlank()) {
            return AuthResult.Error("Email não pode estar vazio")
        }

        return repository.sendPasswordResetEmail(email)
    }
}