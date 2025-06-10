package io.github.faening.lello.core.database.model

import androidx.room.Entity

@Entity(
    tableName = "mood_journal_entry_emotion_option_cross_ref",
    primaryKeys = ["entryId", "emotionOptionId"]
)
data class MoodJournalEntryEmotionOptionCrossRef(
    val entryId: Int,
    val emotionOptionId: Int
)