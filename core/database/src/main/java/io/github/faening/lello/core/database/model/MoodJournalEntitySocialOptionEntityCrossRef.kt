package io.github.faening.lello.core.database.model

import androidx.room.Entity

@Entity(
    tableName = "mood_journals_social_options_cross_ref",
    primaryKeys = ["moodJournalId", "socialOptionId"]
)
data class MoodJournalEntitySocialOptionEntityCrossRef(
    val moodJournalId: Long,
    val socialOptionId: Long
)