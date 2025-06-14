package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "sleep_journals_sleep_activity_options_cross_ref",
    primaryKeys = ["sleepJournalId", "sleepActivityOptionId"],
    indices = [Index(value = ["sleepActivityOptionId"])]
)
data class SleepJournalEntitySleepActivityOptionEntityCrossRef(
    val sleepJournalId: Long,
    val sleepActivityOptionId: Long
)
