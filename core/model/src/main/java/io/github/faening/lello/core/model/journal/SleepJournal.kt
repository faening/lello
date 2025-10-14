package io.github.faening.lello.core.model.journal

import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SleepActivityOption
import io.github.faening.lello.core.model.option.SleepQualityOption
import io.github.faening.lello.core.model.option.SleepSensationOption

data class SleepJournal(
    val id: Long? = null,
    val sleepDuration: SleepDurationOption,
    val sleeplessTime: Int = 0, //  in minutes
    val sleepSensationOptions: List<SleepSensationOption>,
    val sleepQualityOptions: List<SleepQualityOption>,
    val sleepActivityOptions: List<SleepActivityOption>,
    val locationOptions: List<LocationOption>,
    val createdAt: Long
)

enum class SleepDurationOption(val description: String) {
    MORE_THAN_10_HOURS("Mais de 10 horas"),
    BETWEEN_8_TO_10_HOURS("Entre 8 e 10 horas"),
    BETWEEN_6_TO_8_HOURS("Entre 6 e 8 horas"),
    BETWEEN_4_TO_6_HOURS("Entre 4 e 6 horas"),
    LESS_THAN_4_HOURS("Menos de 4 horas");
}