package io.github.faening.lello.core.model.diary

import java.io.Serializable

data class Diary(
    val id: Int?,
    val name: String,
    val description: String,
    val locked: Boolean,
    val active: Boolean,
    val imageUrl: String,
) : Serializable