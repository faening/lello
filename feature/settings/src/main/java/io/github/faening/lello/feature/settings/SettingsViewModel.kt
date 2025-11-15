package io.github.faening.lello.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.BiometricAuthenticationUseCase
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetJournalRewardsNotificationUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetMascotEnergyNotificationUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetMedicationNotificationUseCase
import io.github.faening.lello.core.domain.usecase.theme.ThemePreferenceUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.user.SetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.model.settings.NotificationPreferences
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
    private val setUserBiometricPreferencesUseCase: SetUserBiometricPreferencesUseCase,
    private val getNotificationPreferencesUseCase: GetNotificationPreferencesUseCase,
    private val setJournalRewardsNotificationUseCase: SetJournalRewardsNotificationUseCase,
    private val setMascotEnergyNotificationUseCase: SetMascotEnergyNotificationUseCase,
    private val setMedicationNotificationUseCase: SetMedicationNotificationUseCase
) : ViewModel() {

    private val _isDarkThemeEnabled = MutableStateFlow(false)
    val isDarkThemeEnabled: StateFlow<Boolean> = _isDarkThemeEnabled.asStateFlow()

    private val _notificationPreferences = MutableStateFlow(NotificationPreferences())
    val notificationPreferences: StateFlow<NotificationPreferences> = _notificationPreferences.asStateFlow()

    private val _isBiometricEnabled = MutableStateFlow(false)
    val isBiometricEnabled: StateFlow<Boolean> = _isBiometricEnabled.asStateFlow()

    private val _isBiometricAvailable = MutableStateFlow(false)
    val isBiometricAvailable: StateFlow<Boolean> = _isBiometricAvailable.asStateFlow()

    init {
        loadThemePreferences()
        loadBiometricPreferences()
        loadNotificationPreferences()
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

    private fun loadNotificationPreferences() {
        viewModelScope.launch {
            getNotificationPreferencesUseCase.invoke().collect { preferences ->
                _notificationPreferences.value = preferences
            }
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

    fun toggleJournalRewardNotification(enabled: Boolean) {
        viewModelScope.launch {
            setJournalRewardsNotificationUseCase.invoke(enabled)
        }
    }

    fun toggleMedicationNotification(enabled: Boolean) {
        viewModelScope.launch {
            setMedicationNotificationUseCase.invoke(enabled)
        }
    }

    fun toggleMascotEnergyNotification(enabled: Boolean) {
        viewModelScope.launch {
            setMascotEnergyNotificationUseCase.invoke(enabled)
        }
    }
}