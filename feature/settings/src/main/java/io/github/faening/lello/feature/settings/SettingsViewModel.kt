package io.github.faening.lello.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.terms.TermsAndPrivacyContent
import io.github.faening.lello.core.domain.usecase.authentication.BiometricAuthenticationUseCase
import io.github.faening.lello.core.domain.usecase.authentication.DeleteAccountUseCase
import io.github.faening.lello.core.domain.usecase.authentication.LogoutUseCase
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetJournalRewardsNotificationUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetMascotEnergyNotificationUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetMedicationNotificationUseCase
import io.github.faening.lello.core.domain.usecase.theme.ThemePreferenceUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.user.SetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.model.settings.NotificationPreferences
import io.github.faening.lello.core.model.terms.TermsSection
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
    private val setMedicationNotificationUseCase: SetMedicationNotificationUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _isDarkThemeEnabled = MutableStateFlow(false)
    val isDarkThemeEnabled: StateFlow<Boolean> = _isDarkThemeEnabled.asStateFlow()

    private val _notificationPreferences = MutableStateFlow(NotificationPreferences())
    val notificationPreferences: StateFlow<NotificationPreferences> = _notificationPreferences.asStateFlow()

    private val _isBiometricEnabled = MutableStateFlow(false)
    val isBiometricEnabled: StateFlow<Boolean> = _isBiometricEnabled.asStateFlow()

    private val _isBiometricAvailable = MutableStateFlow(false)
    val isBiometricAvailable: StateFlow<Boolean> = _isBiometricAvailable.asStateFlow()

    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog

    private val _isDeletingAccount = MutableStateFlow(false)
    val isDeletingAccount: StateFlow<Boolean> = _isDeletingAccount

    private val _accountDeletionSucceeded = MutableStateFlow(false)
    val accountDeletionSucceeded: StateFlow<Boolean> = _accountDeletionSucceeded

    private val _accountDeletionError = MutableStateFlow<Throwable?>(null)
    val accountDeletionError: StateFlow<Throwable?> = _accountDeletionError

    private val _termsOfUse = MutableStateFlow(TermsAndPrivacyContent.termsOfUse)
    val termsOfUse: StateFlow<List<TermsSection>> = _termsOfUse

    private val _privacyPolicy = MutableStateFlow(TermsAndPrivacyContent.privacyPolicy)
    val privacyPolicy: StateFlow<List<TermsSection>> = _privacyPolicy

    val lastUpdated: String = TermsAndPrivacyContent.LAST_UPDATED

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

    fun logout() {
        logoutUseCase.invoke()
    }

    fun requestAccountDeletion() {
        _showDeleteDialog.value = true
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun confirmDeleteAccount() {
        _isDeletingAccount.value = true
        viewModelScope.launch {
            _accountDeletionError.value = null
            try {
                val success = deleteAccountUseCase()
                _accountDeletionSucceeded.value = success
            } catch (e: Exception) {
                _accountDeletionError.value = e
            } finally {
                _isDeletingAccount.value = false
                _showDeleteDialog.value = false
            }
        }
    }
}