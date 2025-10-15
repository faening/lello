package io.github.faening.lello.core.domain.usecase.user

import io.github.faening.lello.core.domain.repository.UserRepository
import javax.inject.Inject

class GetUserBiometricPreferencesUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.getBiometricPreference()
    }
}