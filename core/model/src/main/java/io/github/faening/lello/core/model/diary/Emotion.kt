package io.github.faening.lello.core.model.diary

import java.io.Serializable

data class Emotion(
    val id: Long?,
    val word: String,
    val blocked: Boolean = false,
    val active: Boolean = true
) : Serializable