package io.github.faening.lello.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.BiometricAuthenticationUseCase
import io.github.faening.lello.core.domain.usecase.theme.ThemePreferenceUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.user.SetUserBiometricPreferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themePreferenceUseCase: ThemePreferenceUseCase,
    private val biometricAuthenticationUseCase: BiometricAuthenticationUseCase,
    private val getUserBiometricPreferencesUseCase: GetUserBiometricPreferencesUseCase,
    private val setUserBiometricPreferencesUseCase: SetUserBiometricPreferencesUseCase
) : ViewModel() {

    private val _isDarkThemeEnabled = MutableStateFlow(false)
    val isDarkThemeEnabled: StateFlow<Boolean> = _isDarkThemeEnabled.asStateFlow()

    private val _isBiometricEnabled = MutableStateFlow(false)
    val isBiometricEnabled: StateFlow<Boolean> = _isBiometricEnabled.asStateFlow()

    private val _isBiometricAvailable = MutableStateFlow(false)
    val isBiometricAvailable: StateFlow<Boolean> = _isBiometricAvailable.asStateFlow()

    init {
        loadThemePreferences()
        loadBiometricPreferences()
    }

    private fun loadThemePreferences() {
        viewModelScope.launch {
            themePreferenceUseCase.isDarkThemeEnabled.collect { enabled -> _isDarkThemeEnabled.value = enabled }
        }
    }

    private fun loadBiometricPreferences() {
        viewModelScope.launch {
            _isBiometricEnabled.value = getUserBiometricPreferencesUseCase.invoke()
            checkBiometricAvailability()
        }
    }

    fun toggleDarkTheme(enabled: Boolean) {
        _isDarkThemeEnabled.value = enabled
        viewModelScope.launch {
            themePreferenceUseCase.setDarkTheme(enabled)
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