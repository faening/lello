package io.github.faening.lello.core.database.model.moodjournal

import androidx.room.Entity

@Entity(
    tableName = "mood_journals_climate_options_cross_ref",
    primaryKeys = ["moodJournalId", "climateOptionId"]
)
data class MoodJournalEntityClimateOptionEntityCrossRef(
    val moodJournalId: Long,
    val climateOptionId: Long
)