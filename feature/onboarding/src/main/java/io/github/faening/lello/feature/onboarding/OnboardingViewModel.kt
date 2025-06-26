package io.github.faening.lello.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingPreferencesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val useCase: OnboardingPreferencesUseCase
) : ViewModel() {

    val hasSeenOnboarding: Flow<Boolean> = useCase.hasSeenOnboarding

    fun setHasSeenOnboarding() {
        viewModelScope.launch { useCase.setHasSeenOnboarding() }
    }
}
