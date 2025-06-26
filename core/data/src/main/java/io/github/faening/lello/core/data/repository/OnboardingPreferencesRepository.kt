package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.data.preferences.OnboardingPreferences
import io.github.faening.lello.core.domain.repository.OnboardingPreferencesRepository as IOnboardingPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingPreferencesRepository @Inject constructor(
    private val preferences: OnboardingPreferences
) : IOnboardingPreferencesRepository {
    override val hasSeenOnboarding: Flow<Boolean> = preferences.hasSeenOnboarding

    override suspend fun setHasSeenOnboarding() {
        preferences.setHasSeenOnboarding()
    }
}
