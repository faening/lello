package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingPreferencesRepository {
    val hasSeenOnboarding: Flow<Boolean>
    suspend fun setHasSeenOnboarding()
}
