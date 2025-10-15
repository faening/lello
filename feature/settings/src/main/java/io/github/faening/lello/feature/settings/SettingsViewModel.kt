package io.github.faening.lello.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.BiometricAuthenticationUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.user.SetUserBiometricPreferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val biometricAuthenticationUseCase: BiometricAuthenticationUseCase,
    private val getUserBiometricPreferencesUseCase: GetUserBiometricPreferencesUseCase,
    private val setUserBiometricPreferencesUseCase: SetUserBiometricPreferencesUseCase,
) : ViewModel() {

    private val _isBiometricEnabled = MutableStateFlow(false)
    val isBiometricEnabled: StateFlow<Boolean> = _isBiometricEnabled.asStateFlow()

    private val _isBiometricAvailable = MutableStateFlow(false)
    val isBiometricAvailable: StateFlow<Boolean> = _isBiometricAvailable.asStateFlow()

    init {
        viewModelScope.launch {
            _isBiometricEnabled.value = getUserBiometricPreferencesUseCase.invoke()
            checkBiometricAvailability()
        }
    }

    private suspend fun checkBiometricAvailability() {
        _isBiometricAvailable.value = biometricAuthenticationUseCase.shouldUseBiometricAuthentication()
    }

    fun toggleBiometricAuthentication(enabled: Boolean) {
        viewModelScope.launch {
            setUserBiometricPreferencesUseCase.invoke(enabled)
            _isBiometricEnabled.value = enabled
        }
    }
}