package io.github.faening.lello.core.model.option

import java.io.Serializable

interface JournalOption : Serializable {
    val id: Long?
    val description: String
    val blocked: Boolean
    val active: Boolean
    val selected: Boolean
}