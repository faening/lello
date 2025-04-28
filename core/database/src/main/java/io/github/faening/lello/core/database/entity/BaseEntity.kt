package io.github.faening.lello.core.database.entity

import kotlinx.datetime.Instant

abstract class BaseEntity {
    abstract val id: Long
    abstract val createdAt: Instant
    abstract val updatedAt: Instant
}