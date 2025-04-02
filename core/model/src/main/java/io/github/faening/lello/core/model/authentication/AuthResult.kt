package io.github.faening.lello.core.model.authentication

sealed class AuthResult<T> {
    class Success<T>(val data: T) : AuthResult<T>()
    class Error<T>(val message: String) : AuthResult<T>()
    class Loading<T> : AuthResult<T>()
}