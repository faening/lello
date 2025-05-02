package io.github.faening.lello.core.model.diary

import java.io.Serializable

data class Journal(
    val id: Int?,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val blocked: Boolean,
    val active: Boolean,
) : Serializable