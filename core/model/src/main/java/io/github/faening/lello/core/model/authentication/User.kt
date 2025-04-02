package io.github.faening.lello.core.model.authentication

data class User(
    val id: String,
    val email: String?,
    val displayName: String?,
    val photoUrl: String?
)