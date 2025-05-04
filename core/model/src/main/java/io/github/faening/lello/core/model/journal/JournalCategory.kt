package io.github.faening.lello.core.model.journal

import java.io.Serializable

data class JournalCategory(
    val id: Int?,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val blocked: Boolean = false,
    val active: Boolean = true,
) : Serializable