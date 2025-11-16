package io.github.faening.lello.core.domain.usecase.authentication

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke() {
        firebaseAuth.signOut()
        // TODO: Limpar dados locais
    }
}