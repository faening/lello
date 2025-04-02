package io.github.faening.lello.core.authentication.mapper

import com.google.firebase.auth.FirebaseUser
import io.github.faening.lello.core.model.authentication.User

internal fun FirebaseUser.toUser(): User {
    return User(
        id = uid,
        email = email,
        displayName = displayName,
        photoUrl = photoUrl?.toString()
    )
}