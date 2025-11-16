package io.github.faening.lello.core.domain.usecase.authentication

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.faening.lello.core.domain.repository.UserRepository
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import io.github.faening.lello.core.model.authentication.AuthResult
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BiometricAuthenticationUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userRepository: UserRepository,
    private val getUserEmailUseCase: GetUserEmailUseCase
) {

    /**
     * Determina se a autenticação biométrica deve ser usada com base em todas as condições necessárias.
     *
     * @return true se todas as condições para usar biometria forem atendidas
     */
    suspend fun shouldUseBiometricAuthentication(): Boolean {
        // Verifica hardware disponível
        val isHardwareAvailable = isBiometricAvailable()

        // Verifica se o usuário habilitou a biometria
        val isBiometricPreferenceEnabled = userRepository.getBiometricPreference()

        // Verifica se existe email salvo
        val savedEmail = getUserEmailUseCase()

        // Só habilita biometria se todas as condições forem atendidas
        return isHardwareAvailable && isBiometricPreferenceEnabled && !savedEmail.isNullOrEmpty()
    }

    /**
     * Verifica se a autenticação biométrica está disponível no dispositivo.
     *
     * @return true se a biometria estiver disponível para uso
     */
    private fun isBiometricAvailable(): Boolean {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            else -> false
        }
    }

    /**
     * Inicia a autenticação biométrica.
     *
     * @param activity FragmentActivity necessária para exibir o prompt biométrico
     * @param title Título a ser exibido no prompt
     * @param subtitle Subtítulo a ser exibido no prompt
     * @param negativeButtonText Texto do botão negativo
     * @return AuthResult com o resultado da autenticação
     */
    suspend fun authenticate(
        activity: FragmentActivity,
        title: String,
        subtitle: String,
        negativeButtonText: String
    ): AuthResult<Any> = suspendCoroutine { continuation ->
        val executor = ContextCompat.getMainExecutor(context)

        val biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    continuation.resume(AuthResult.Success(Unit))
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    continuation.resume(AuthResult.Error(errString.toString()))
                }

                override fun onAuthenticationFailed() {
                    continuation.resume(AuthResult.Error("Falha na autenticação biométrica"))
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setNegativeButtonText(negativeButtonText)
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}