package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.SleepJournal

@Entity(tableName = "sleep_journals")
data class SleepJournalEntity(
    @PrimaryKey(autoGenerate = true) val sleepJournalId: Long,
    val sleeplessTime: Int = 0, // in minutes
    @ColumnInfo(name = "created_at") val createdAt: Long,
)

fun SleepJournalEntity.toModel(): SleepJournal {
    return SleepJournal(
        id = sleepJournalId,
        sleepDuration = null,
        sleeplessTime = sleeplessTime,
        sleepSensationOptions = emptyList(),
        sleepQualityOptions = emptyList(),
        sleepActivityOptions = emptyList(),
        locationOptions = emptyList(),
        createdAt = createdAt,
    )
}

fun SleepJournal.toEntity(): SleepJournalEntity {
    return SleepJournalEntity(
        sleepJournalId = id ?: 0L,
        sleeplessTime = sleeplessTime,
        createdAt = createdAt,
    )
}