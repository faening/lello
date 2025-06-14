package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "sleep_journals_sleep_sensation_options_cross_ref",
    primaryKeys = ["sleepJournalId", "sleepSensationOptionId"],
    indices = [Index(value = ["sleepSensationOptionId"])]
)
data class SleepJournalEntitySleepSensationOptionEntityCrossRef(
    val sleepJournalId: Long,
    val sleepSensationOptionId: Long
)
