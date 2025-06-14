package io.github.faening.lello.core.database.model.journal.mood

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "mood_journals_climate_options_cross_ref",
    primaryKeys = ["moodJournalId", "climateOptionId"],
    indices = [Index(value = ["climateOptionId"])]
)
data class MoodJournalEntityClimateOptionEntityCrossRef(
    val moodJournalId: Long,
    val climateOptionId: Long
)