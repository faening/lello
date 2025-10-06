package io.github.faening.lello.core.domain.usecase.user

import io.github.faening.lello.core.domain.repository.UserRepository
import jakarta.inject.Inject

class GetUserEmailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): String? {
        return repository.getUserEmail()
    }
}