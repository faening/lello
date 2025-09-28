package io.github.faening.lello.feature.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.SignInUseCase
import io.github.faening.lello.core.domain.usecase.authentication.SignUpUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import io.github.faening.lello.core.domain.usecase.user.SaveUserEmailUseCase
import io.github.faening.lello.core.model.authentication.AuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val saveUserEmailUseCase: SaveUserEmailUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState: StateFlow<AuthenticationUiState> = _uiState.asStateFlow()

    init {
        loadSavedEmail()
    }

    /**
     * Carrega o email salvo nas preferências e atualiza o estado da UI.
     */
    private fun loadSavedEmail() {
        viewModelScope.launch {
            val email = getUserEmailUseCase()
            _uiState.update { it.copy(savedEmail = email) }
        }
    }

    /**
     * Realiza o login do usuário com email e senha fornecidos e atualiza o estado da UI.
     *
     * @param email O email do usuário.
     * @param password A senha do usuário.
     */
    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val result = signInUseCase(email, password)
            when (result) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, isSignInSuccessful = true)
                }

                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = result.message)
                }

                is AuthResult.Loading<*> -> {}
            }
        }
    }

    /**
     * Reseta o estado de sucesso do login para falso. Isso é útil para evitar múltiplas navegações
     * ou ações baseadas no estado de sucesso do login.
     */
    fun resetSignInState() {
        _uiState.value = _uiState.value.copy(isSignInSuccessful = false)
    }

    /**
     * Realiza o cadastro do usuário com email e senha fornecidos e atualiza o estado da UI.
     *
     * @param email O email do usuário.
     * @param password A senha do usuário.
     */
    fun signUpWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val result = signUpUseCase(email, password)
            when (result) {
                is AuthResult.Success -> {
                    saveUserEmailUseCase(email) // Armazena o email do usuário no DataStore
                    _uiState.value = _uiState.value.copy(isLoading = false, isSignUpSuccessful = true)
                }

                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = result.message)
                }

                is AuthResult.Loading<*> -> {}
            }
        }
    }

    /**
     * Reseta o estado de sucesso do cadastro para falso. Isso é útil para evitar múltiplas navegações
     * ou ações baseadas no estado de sucesso do cadastro.
     */
    fun resetSignUpState() {
        _uiState.value = _uiState.value.copy(isSignUpSuccessful = false)
    }

    /**
     * Limpa a mensagem de erro atual no estado da UI.
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

/**
 * Estado da UI para as telas de autenticação.
 *
 * @param isLoading Indica se uma operação de autenticação está em andamento.
 * @param errorMessage Mensagem de erro, se houver.
 * @param isSignUpSuccessful Indica se o cadastro foi bem-sucedido.
 * @param isSignInSuccessful Indica se o login foi bem-sucedido.
 * @param savedEmail O email salvo nas preferências, se houver.
 */
data class AuthenticationUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSignUpSuccessful: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val savedEmail: String? = null
)