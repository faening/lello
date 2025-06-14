package io.github.faening.lello.core.database.model.journal.mood

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "mood_journals_social_options_cross_ref",
    primaryKeys = ["moodJournalId", "socialOptionId"],
    indices = [Index(value = ["socialOptionId"])]
)
data class MoodJournalEntitySocialOptionEntityCrossRef(
    val moodJournalId: Long,
    val socialOptionId: Long
)