package io.github.faening.lello.core.model.diary

import kotlinx.datetime.Instant

data class Diary(
    val id: String,
    val createdAt: Instant,
    val updatedAt: Instant,

    val name: String,
    val description: String,
    val locked: Boolean,
    val active: Boolean,
    val imageUrl: String,
)