package io.github.faening.lello.core.model.diary

import java.io.Serializable

data class Climate(
    val id: Long?,
    val weatherType: String,
    val blocked: Boolean = false,
    val active: Boolean = true
) : Serializable