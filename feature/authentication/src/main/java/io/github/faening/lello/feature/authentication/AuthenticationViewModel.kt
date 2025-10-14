package io.github.faening.lello.feature.authentication

import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.BiometricAuthenticationUseCase
import io.github.faening.lello.core.domain.usecase.authentication.ForgotPasswordUseCase
import io.github.faening.lello.core.domain.usecase.authentication.SignInWithEmailAndPasswordUseCase
import io.github.faening.lello.core.domain.usecase.authentication.SignInWithGoogleUseCase
import io.github.faening.lello.core.domain.usecase.authentication.SignUpWithEmailAndPasswordUseCase
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import io.github.faening.lello.core.domain.usecase.user.SaveUserEmailUseCase
import io.github.faening.lello.core.model.authentication.AuthResult
import io.github.faening.lello.feature.home.HomeDestinations
import io.github.faening.lello.feature.onboarding.OnboardingDestinations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val biometricAuthenticationUseCase: BiometricAuthenticationUseCase,
    private val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val saveUserEmailUseCase: SaveUserEmailUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val onboardingUseCase: OnboardingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState: StateFlow<AuthenticationUiState> = _uiState.asStateFlow()

    private val _hasSeenOnboarding: Flow<Boolean> = MutableStateFlow(false)

    private val _canUseBiometricAuth = MutableStateFlow(false)
    val canUseBiometricAuth: StateFlow<Boolean> = _canUseBiometricAuth

    init {
        loadSavedEmail()
        loadOnboardingState()
        loadIsBiometricAvailable()
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
     * Carrega o estado de onboarding do usuário e atualiza o fluxo correspondente.
     */
    private fun loadOnboardingState() {
        viewModelScope.launch {
            onboardingUseCase.hasSeenOnboarding.collect { hasSeen ->
                // _uiState.update { it.copy(isLoading = false) }
                (_hasSeenOnboarding as MutableStateFlow).value = hasSeen
            }
        }
    }

    fun loadIsBiometricAvailable() {
        viewModelScope.launch {
            runCatching {
                _canUseBiometricAuth.value = biometricAuthenticationUseCase.shouldUseBiometricAuthentication()
            }.onFailure {
                _canUseBiometricAuth.value = false
            }
        }
    }

    /**
     * Retorna o próximo destino de navegação com base no estado de onboarding do usuário.
     *
     * @return Um fluxo que emite o próximo destino de navegação.
     */
    fun getNextDestination(): Flow<String> = _hasSeenOnboarding.map { hasSeenOnboarding ->
        if (hasSeenOnboarding) {
            HomeDestinations.GRAPH
        } else {
            OnboardingDestinations.GRAPH
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

            val result = signInWithEmailAndPasswordUseCase(email, password)
            when (result) {
                is AuthResult.Success -> {
                    if (_uiState.value.savedEmail.isNullOrEmpty()) {
                        saveUserEmailUseCase(email)
                        _uiState.value = _uiState.value.copy(savedEmail = email)
                    }
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

            val result = signUpWithEmailAndPasswordUseCase(email, password)
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

    fun buildGoogleSignInRequest(): GetCredentialRequest {
        return signInWithGoogleUseCase.buildSignInRequest()
    }

    /**
     * Realiza o login do usuário usando o token do Google e atualiza o estado da UI.
     *
     * @param response A resposta do Google contendo o token de autenticação.
     */
    fun signInWithGoogle(response: GetCredentialResponse) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result = signInWithGoogleUseCase(response)) {
                is AuthResult.Success -> {
                    _uiState.update { it.copy(isLoading = false, isSignInSuccessful = true) }
                }
                is AuthResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                else -> {}
            }
        }
    }
    /**
     * Autentica o usuário usando biometria.
     *
     * @param activity FragmentActivity necessária para o BiometricPrompt
     */
    fun authenticateWithBiometric(activity: FragmentActivity) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = biometricAuthenticationUseCase.authenticate(
                activity = activity,
                title = "Use sua digital para continuar",
                subtitle = "",
                negativeButtonText = "Cancelar"
            )

            when (result) {
                is AuthResult.Success -> {
                    // Se tiver email salvo, faça login automático com ele
                    _uiState.value.savedEmail?.let { email ->
                        // Aqui você pode fazer login automático se tiver o token salvo
                        // ou apenas marcar como autenticado com sucesso
                        _uiState.update { it.copy(isLoading = false, isSignInSuccessful = true) }
                    } ?: _uiState.update {
                        it.copy(isLoading = false, errorMessage = "Email não encontrado para login biométrico")
                    }
                }

                is AuthResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }

                else -> {}
            }
        }
    }

    /**
     * Envia um email de recuperação de senha para o email fornecido.
     *
     * @param email O email para o qual enviar as instruções de recuperação.
     */
    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            when (val result = forgotPasswordUseCase(email)) {
                is AuthResult.Success -> {
                    _uiState.update { it.copy(isLoading = false, isPasswordResetSuccessful = true) }
                }
                is AuthResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                else -> {}
            }
        }
    }

    /**
     * Reseta o estado de sucesso da recuperação de senha para falso.
     */
    fun resetPasswordResetState() {
        _uiState.update { it.copy(isPasswordResetSuccessful = false) }
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
    val isPasswordResetSuccessful: Boolean = false,
    val savedEmail: String? = null
)