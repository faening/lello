package io.github.faening.lello.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingUseCase: OnboardingUseCase
) : ViewModel() {

    val hasSeenOnboarding: Flow<Boolean> = onboardingUseCase.hasSeenOnboarding

    fun setHasSeenOnboarding() {
        viewModelScope.launch { onboardingUseCase.setHasSeenOnboarding() }
    }
}
