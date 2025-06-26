package io.github.faening.lello.core.model.mascot

/** Keeps track of all mascot vitality changes. */
data class MascotVitalityHistory(
    val id: Long = 0L,
    val changeType: String,
    val value: Int,
    val delta: Int,
    val source: String,
    val createdAt: Long
)
