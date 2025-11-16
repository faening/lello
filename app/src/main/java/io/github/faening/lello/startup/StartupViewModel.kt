package io.github.faening.lello.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.BiometricAuthenticationUseCase
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val onboardingUseCase: OnboardingUseCase,
    private val biometricAuthenticationUseCase: BiometricAuthenticationUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val hasSeenOnboarding: Flow<Boolean> = onboardingUseCase.hasSeenOnboarding

    private val _isUserAuthenticated = MutableStateFlow<Boolean?>(null)
    val isUserAuthenticated: StateFlow<Boolean?> = _isUserAuthenticated

    private val _canUseBiometricAuth = MutableStateFlow(false)
    val canUseBiometricAuth: StateFlow<Boolean> = _canUseBiometricAuth

    init {
        checkAuthenticationState()
        checkBiometricAvailability()

        firebaseAuth.addAuthStateListener { auth ->
            val isAuthenticated = auth.currentUser != null
            _isUserAuthenticated.value = isAuthenticated
            _isLoading.value = false
        }
    }

    private fun checkAuthenticationState() {
        viewModelScope.launch {
            try {
                firebaseAuth.currentUser?.reload()?.await()
                val isAuthenticated = firebaseAuth.currentUser != null
                _isUserAuthenticated.value = isAuthenticated
            } catch (_: Exception) {
                _isUserAuthenticated.value = false
            } finally {
                delay(1500)
                if (_isUserAuthenticated.value == null) {
                    _isUserAuthenticated.value = false
                }
                _isLoading.value = false
            }
        }
    }

    /**
     * Verifica se a autenticação biométrica está disponível e se há um email salvo.
     */
    private fun checkBiometricAvailability() {
        viewModelScope.launch {
            runCatching {
                _canUseBiometricAuth.value = biometricAuthenticationUseCase.shouldUseBiometricAuthentication()
            }.onFailure {
                _canUseBiometricAuth.value = false
            }
        }
    }
}
