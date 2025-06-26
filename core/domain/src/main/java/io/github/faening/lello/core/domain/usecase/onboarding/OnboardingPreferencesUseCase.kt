package io.github.faening.lello.core.domain.usecase.onboarding

import io.github.faening.lello.core.domain.repository.OnboardingPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingPreferencesUseCase @Inject constructor(
    private val repository: OnboardingPreferencesRepository
) {
    val hasSeenOnboarding: Flow<Boolean> = repository.hasSeenOnboarding

    suspend fun setHasSeenOnboarding() {
        repository.setHasSeenOnboarding()
    }
}
