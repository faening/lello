package io.github.faening.lello.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingPreferencesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    useCase: OnboardingPreferencesUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val hasSeenOnboarding: Flow<Boolean> = useCase.hasSeenOnboarding
    private val _isUserAuthenticated = MutableStateFlow<Boolean?>(null)
    val isUserAuthenticated: StateFlow<Boolean?> = _isUserAuthenticated

    init {
        // Verificação única inicial
        checkAuthState()

        // Observer para mudanças futuras
        firebaseAuth.addAuthStateListener { auth ->
            _isUserAuthenticated.value = auth.currentUser != null
            _isLoading.value = false
        }
    }

    private fun checkAuthState() {
        viewModelScope.launch {
            // Garantir que o Firebase tenha tempo de inicializar
            try {
                firebaseAuth.currentUser?.reload()?.await()
                _isUserAuthenticated.value = firebaseAuth.currentUser != null
            } catch (e: Exception) {
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
}

