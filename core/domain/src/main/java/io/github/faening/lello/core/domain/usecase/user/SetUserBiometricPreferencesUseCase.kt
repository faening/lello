package io.github.faening.lello.core.domain.usecase.user

import io.github.faening.lello.core.domain.repository.UserRepository
import javax.inject.Inject

class SetUserBiometricPreferencesUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.setBiometricPreference(enabled)
    }
}