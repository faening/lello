package io.github.faening.lello.core.model.journal

import java.io.Serializable

interface JournalOption : Serializable {
    val id: Int?
    val description: String
    val blocked: Boolean
    val active: Boolean
    val selected: Boolean
}