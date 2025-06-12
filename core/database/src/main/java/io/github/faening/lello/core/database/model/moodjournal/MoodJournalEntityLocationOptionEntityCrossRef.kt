package io.github.faening.lello.core.database.model.moodjournal

import androidx.room.Entity

@Entity(
    tableName = "mood_journals_location_options_cross_ref",
    primaryKeys = ["moodJournalId", "locationOptionId"]
)
data class MoodJournalEntityLocationOptionEntityCrossRef(
    val moodJournalId: Long,
    val locationOptionId: Long
)