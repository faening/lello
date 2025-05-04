package io.github.faening.lello.core.model.diary

import java.io.Serializable

data class EmotionOption(
    val id: Int?,
    val description: String,
    val blocked: Boolean = false,
    val active: Boolean = true
) : Serializable