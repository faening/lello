package io.github.faening.lello.core.database.model

import androidx.room.Entity

@Entity(
    tableName = "mood_journal_entry_health_option_cross_ref",
    primaryKeys = ["entryId", "healthOptionId"]
)
data class MoodJournalEntryHealthOptionCrossRef(
    val entryId: Int,
    val healthOptionId: Int
)