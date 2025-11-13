package io.github.faening.lello.core.domain.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.toInstant(): Instant = Instant.ofEpochMilli(this)

fun Instant.toEpochMillis(): Long = this.toEpochMilli()

fun Long.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()): LocalDateTime =
    Instant.ofEpochMilli(this).atZone(zone).toLocalDateTime()

fun LocalDateTime.toEpochMillis(zone: ZoneId = ZoneId.systemDefault()): Long =
    this.atZone(zone).toInstant().toEpochMilli()

fun Long.isSameDay(date: LocalDate): Boolean {
    val thisDate = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
    return thisDate == date
}

/**
 * Converte um [LocalTime] para epoch millis em uma data específica.
 *
 * @param date A data para a qual o tempo será aplicado.
 * @param zone O fuso horário a ser usado. Padrão é o fuso
 * @return epoch millis correspondente ao [LocalTime] na [date] especificada.
 */
fun LocalTime.toEpochMillisOnDate(date: LocalDate, zone: ZoneId = ZoneId.systemDefault()): Long {
    val dateTime = LocalDateTime.of(date, this)
    return dateTime.atZone(zone).toInstant().toEpochMilli()
}

fun Long.formatTimestamp(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale("pt", "BR"))
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
        .format(formatter)
}