package io.github.faening.lello.core.model.journal

data class SleepJournal(
    val id: Long? = null,
    val date: Long,
    val duration: Int = 0, // in minutes
    val sleeplessTime: Int = 0, //  in minutes
    val sleepSensationOptions: List<SleepSensationOption>,
    val sleepQualityOptions: List<SleepQualityOption>,
    val sleepActivityOptions: List<SleepActivityOption>,
    val locationOptions: List<LocationOption>
)