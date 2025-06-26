package io.github.faening.lello.core.model.mascot

/** Represents the current mascot status. */
data class MascotStatus(
    val id: Int = 1,
    val vitality: Int,
    val lastUpdatedAt: Long
)
