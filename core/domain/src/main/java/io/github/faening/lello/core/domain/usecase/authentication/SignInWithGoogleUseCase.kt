package io.github.faening.lello.core.domain.usecase.authentication

import android.content.Context
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.faening.lello.core.domain.R
import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import java.util.UUID
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: AuthenticationRepository
) {

    /**
     * Constrói a requisição para o Google Sign-In usando o CredentialManager.
     */
    fun buildSignInRequest(): GetCredentialRequest {
        val nonce = UUID.randomUUID().toString()
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .setNonce(nonce)
            .build()

        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    /**
     * Processa a resposta do CredentialManager e autentica com o Firebase.
     */
    suspend operator fun invoke(response: GetCredentialResponse): AuthResult<Unit> {
        return try {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(response.credential.data)
            repository.signInWithGoogle(googleIdTokenCredential)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Falha ao processar a credencial do Google.")
        }
    }
}