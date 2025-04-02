package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(): AuthResult<Unit> = repository.signOut()
}