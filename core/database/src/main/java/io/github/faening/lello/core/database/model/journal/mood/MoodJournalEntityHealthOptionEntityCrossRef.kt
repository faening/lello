package io.github.faening.lello.core.database.model.journal.mood

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "mood_journals_health_options_cross_ref",
    primaryKeys = ["moodJournalId", "healthOptionId"],
    indices = [Index(value = ["healthOptionId"])]
)
data class MoodJournalEntityHealthOptionEntityCrossRef(
    val moodJournalId: Long,
    val healthOptionId: Long
)