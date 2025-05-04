package io.github.faening.lello.core.database.model

import androidx.room.PrimaryKey

open class OptionBaseEntity {
    @PrimaryKey(autoGenerate = true) open val id: Int = 0
    open val description: String = ""
    open val blocked: Boolean = false
    open val active: Boolean = true
}