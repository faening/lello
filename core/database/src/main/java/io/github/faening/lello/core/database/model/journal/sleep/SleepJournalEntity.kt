package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.SleepJournal

@Entity(tableName = "sleep_journals")
data class SleepJournalEntity(
    @PrimaryKey(autoGenerate = true) val sleepJournalId: Long,
    val date: Long,
    val duration: Int = 0, // in minutes
    val sleeplessTime: Int = 0, // in minutes
)

fun SleepJournalEntity.toModel(): SleepJournal {
    return SleepJournal(
        id = sleepJournalId,
        date = date,
        duration = duration,
        sleeplessTime = sleeplessTime,
        sleepSensationOptions = emptyList(),
        sleepQualityOptions = emptyList(),
        sleepActivityOptions = emptyList(),
        locationOptions = emptyList()
    )
}

fun SleepJournal.toEntity(): SleepJournalEntity {
    return SleepJournalEntity(
        sleepJournalId = id ?: 0L,
        date = date,
        duration = duration,
        sleeplessTime = sleeplessTime,
    )
}