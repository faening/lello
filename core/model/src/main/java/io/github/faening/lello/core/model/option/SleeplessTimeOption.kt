package io.github.faening.lello.core.model.option

data class SleeplessTimeOption(
    val minutes: Int,
    val label: String
)

val SleeplessTimeOptions = listOf(
    SleeplessTimeOption(5, "5m"),
    SleeplessTimeOption(10, "10m"),
    SleeplessTimeOption(15, "15m"),
    SleeplessTimeOption(30, "30m"),
    SleeplessTimeOption(45, "45m"),
    SleeplessTimeOption(60, "1h"),
    SleeplessTimeOption(90, "1h 30m"),
    SleeplessTimeOption(120, "2h"),
    SleeplessTimeOption(150, "mais de 2h"),
)