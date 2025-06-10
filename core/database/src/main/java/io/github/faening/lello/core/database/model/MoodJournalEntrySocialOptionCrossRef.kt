package io.github.faening.lello.core.database.model

import androidx.room.Entity

@Entity(
    tableName = "mood_journal_entry_social_option_cross_ref",
    primaryKeys = ["entryId", "socialOptionId"]
)
data class MoodJournalEntrySocialOptionCrossRef(
    val entryId: Int,
    val socialOptionId: Int
)