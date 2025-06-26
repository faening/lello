package io.github.faening.lello.startup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingPreferencesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    useCase: OnboardingPreferencesUseCase
) : ViewModel() {
    val hasSeenOnboarding: Flow<Boolean> = useCase.hasSeenOnboarding
}
