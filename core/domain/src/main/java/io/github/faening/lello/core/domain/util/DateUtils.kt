package io.github.faening.lello.core.domain.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun Long.toInstant(): Instant = Instant.ofEpochMilli(this)

fun Instant.toEpochMillis(): Long = this.toEpochMilli()

fun Long.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()): LocalDateTime =
    Instant.ofEpochMilli(this).atZone(zone).toLocalDateTime()

fun LocalDateTime.toEpochMillis(zone: ZoneId = ZoneId.systemDefault()): Long =
    this.atZone(zone).toInstant().toEpochMilli()