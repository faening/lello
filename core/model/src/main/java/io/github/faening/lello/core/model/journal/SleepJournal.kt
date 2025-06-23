package io.github.faening.lello.core.model.journal

import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SleepActivityOption
import io.github.faening.lello.core.model.option.SleepDurationOption
import io.github.faening.lello.core.model.option.SleepQualityOption
import io.github.faening.lello.core.model.option.SleepSensationOption

data class SleepJournal(
    val id: Long? = null,
    val sleepDuration: SleepDurationOption?,
    val sleeplessTime: Int = 0, //  in minutes
    val sleepSensationOptions: List<SleepSensationOption>,
    val sleepQualityOptions: List<SleepQualityOption>,
    val sleepActivityOptions: List<SleepActivityOption>,
    val locationOptions: List<LocationOption>,
    val createdAt: Long
)