package io.github.faening.lello.core.model.authentication

/**
 * Estado centralizado da autenticação do usuário, usado em todo o aplicativo.
 */
sealed class AuthenticationState {
    /**
     * Estado inicial, sem operação de autenticação em andamento.
     */
    data object Idle : AuthenticationState()

    /**
     * Uma operação de autenticação está em andamento.
     */
    data object Loading : AuthenticationState()

    /**
     * A autenticação foi bem-sucedida.
     */
    data object Success : AuthenticationState()

    /**
     * A autenticação falhou.
     *
     * @param message Mensagem descrevendo o erro.
     */
    data class Error(val message: String) : AuthenticationState()
}
