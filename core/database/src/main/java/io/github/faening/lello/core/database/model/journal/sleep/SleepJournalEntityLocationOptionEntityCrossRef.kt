package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "sleep_journals_location_options_cross_ref",
    primaryKeys = ["sleepJournalId", "locationOptionId"],
    indices = [Index(value = ["locationOptionId"])]
)
data class SleepJournalEntityLocationOptionEntityCrossRef(
    val sleepJournalId: Long,
    val locationOptionId: Long
)
