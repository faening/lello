package io.github.faening.lello.core.model.journal

import java.io.Serializable

data class SocialOption (
    val id: Int?,
    val description: String,
    val blocked: Boolean = false,
    val active: Boolean = true
) : Serializable