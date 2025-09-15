package io.github.faening.lello.feature.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.SignInUseCase
import io.github.faening.lello.core.domain.usecase.authentication.SignUpUseCase
import io.github.faening.lello.core.model.authentication.AuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState: StateFlow<AuthenticationUiState> = _uiState.asStateFlow()

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val result = signInUseCase(email, password)
            when (result) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignInSuccessful = true
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is AuthResult.Loading<*> -> TODO()
            }
        }
    }

    fun resetSignInState() {
        _uiState.value = _uiState.value.copy(isSignInSuccessful = false)
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val result = signUpUseCase(email, password)
            when (result) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignUpSuccessful = true
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is AuthResult.Loading<*> -> TODO()
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun resetSignUpState() {
        _uiState.value = _uiState.value.copy(isSignUpSuccessful = false)
    }
}

data class AuthenticationUiState(
    val isLoading: Boolean = false,
    val isSignUpSuccessful: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val errorMessage: String? = null
)
