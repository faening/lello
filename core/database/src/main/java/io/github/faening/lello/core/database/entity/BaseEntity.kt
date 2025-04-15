package io.github.faening.lello.core.database.entity

import androidx.room.PrimaryKey
import kotlinx.datetime.Clock.System
import kotlinx.datetime.Instant
import java.util.UUID

abstract class BaseEntity {
    @PrimaryKey
    val id: UUID = UUID.randomUUID()

    val createdAt: Instant = System.now()
    var updatedAt: Instant = System.now()
}