package io.github.faening.lello.core.domain.repository

import android.content.Context

interface OnboardingResources {
    suspend fun setHasSeenOnboarding(context: Context)
    suspend fun hasSeenOnboardingFlow(context: Context)
}