package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(): Flow<User?> = repository.getCurrentUser()
}