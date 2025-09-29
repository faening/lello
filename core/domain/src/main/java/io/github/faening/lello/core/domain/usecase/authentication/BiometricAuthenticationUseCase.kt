package io.github.faening.lello.core.domain.usecase.authentication

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.faening.lello.core.model.authentication.AuthResult
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BiometricAuthenticationUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {

    /**
     * Verifica se a autenticação biométrica está disponível no dispositivo.
     *
     * @return true se a biometria estiver disponível para uso
     */
    fun isBiometricAvailable(): Boolean {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
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
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}