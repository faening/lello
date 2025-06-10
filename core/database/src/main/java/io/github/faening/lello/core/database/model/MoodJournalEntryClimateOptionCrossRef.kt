package io.github.faening.lello.core.database.model

import androidx.room.Entity

@Entity(
    tableName = "mood_journal_entry_climate_option_cross_ref",
    primaryKeys = ["entryId", "climateOptionId"]
)
data class MoodJournalEntryClimateOptionCrossRef(
    val entryId: Int,
    val climateOptionId: Int
)