package io.github.faening.lello.core.database.model.moodjournal

import androidx.room.Entity

@Entity(
    tableName = "mood_journals_health_options_cross_ref",
    primaryKeys = ["moodJournalId", "healthOptionId"]
)
data class MoodJournalEntityHealthOptionEntityCrossRef(
    val moodJournalId: Long,
    val healthOptionId: Long
)