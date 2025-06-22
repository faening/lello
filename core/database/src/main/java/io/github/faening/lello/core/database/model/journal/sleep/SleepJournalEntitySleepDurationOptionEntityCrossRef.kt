package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "sleep_journals_sleep_duration_options_cross_ref",
    primaryKeys = ["sleepJournalId", "sleepDurationOptionId"],
    indices = [Index(value = ["sleepDurationOptionId"])]
)
data class SleepJournalEntitySleepDurationOptionEntityCrossRef(
    val sleepJournalId: Long,
    val sleepDurationOptionId: Long
)
