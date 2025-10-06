package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    val hasSeenOnboarding: Flow<Boolean>
    suspend fun setHasSeenOnboarding()
}