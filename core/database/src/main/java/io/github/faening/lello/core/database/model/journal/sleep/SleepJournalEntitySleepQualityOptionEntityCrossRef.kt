package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "sleep_journals_sleep_quality_options_cross_ref",
    primaryKeys = ["sleepJournalId", "sleepQualityOptionId"],
    indices = [Index(value = ["sleepQualityOptionId"])]
)
data class SleepJournalEntitySleepQualityOptionEntityCrossRef(
    val sleepJournalId: Long,
    val sleepQualityOptionId: Long
)
