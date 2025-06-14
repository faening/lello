package io.github.faening.lello.core.database.model.journal.mood

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "mood_journals_location_options_cross_ref",
    primaryKeys = ["moodJournalId", "locationOptionId"],
    indices = [Index(value = ["locationOptionId"])]
)
data class MoodJournalEntityLocationOptionEntityCrossRef(
    val moodJournalId: Long,
    val locationOptionId: Long
)