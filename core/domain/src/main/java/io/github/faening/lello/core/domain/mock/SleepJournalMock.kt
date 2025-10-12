package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.SleepDurationOption
import io.github.faening.lello.core.model.journal.SleepJournal

object SleepJournalMock {
    val list = listOf(
        SleepJournal(
            id = 1,
            sleepDuration = SleepDurationOption.MORE_THAN_10_HOURS,
            sleeplessTime = 15,
            sleepSensationOptions = SleepSensationOptionMock.list.take(1),
            sleepQualityOptions = SleepQualityOptionMock.list.take(1),
            sleepActivityOptions = SleepActivityOptionMock.list.take(1),
            locationOptions = LocationOptionMock.list.take(1),
            createdAt = System.currentTimeMillis()
        ),
        SleepJournal(
            id = 2,
            sleepDuration = SleepDurationOption.BETWEEN_8_TO_10_HOURS,
            sleeplessTime = 20,
            sleepSensationOptions = SleepSensationOptionMock.list.take(2),
            sleepQualityOptions = SleepQualityOptionMock.list.take(2),
            sleepActivityOptions = SleepActivityOptionMock.list.take(2),
            locationOptions = LocationOptionMock.list.take(2),
            createdAt = System.currentTimeMillis()
        ),
        SleepJournal(
            id = 3,
            sleepDuration = SleepDurationOption.BETWEEN_6_TO_8_HOURS,
            sleeplessTime = 30,
            sleepSensationOptions = SleepSensationOptionMock.list.take(3),
            sleepQualityOptions = SleepQualityOptionMock.list.take(3),
            sleepActivityOptions = SleepActivityOptionMock.list.take(3),
            locationOptions = LocationOptionMock.list.take(3),
            createdAt = System.currentTimeMillis()
        ),
        SleepJournal(
            id = 4,
            sleepDuration = SleepDurationOption.BETWEEN_4_TO_6_HOURS,
            sleeplessTime = 45,
            sleepSensationOptions = SleepSensationOptionMock.list.take(4),
            sleepQualityOptions = SleepQualityOptionMock.list.take(4),
            sleepActivityOptions = SleepActivityOptionMock.list.take(4),
            locationOptions = LocationOptionMock.list.take(4),
            createdAt = System.currentTimeMillis()
        ),
        SleepJournal(
            id = 5,
            sleepDuration = SleepDurationOption.LESS_THAN_4_HOURS,
            sleeplessTime = 60,
            sleepSensationOptions = SleepSensationOptionMock.list.take(5),
            sleepQualityOptions = SleepQualityOptionMock.list.take(5),
            sleepActivityOptions = SleepActivityOptionMock.list.take(5),
            locationOptions = LocationOptionMock.list.take(5),
            createdAt = System.currentTimeMillis()
        )
    )
}
