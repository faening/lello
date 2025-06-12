package io.github.faening.lello.core.database.model.moodjournal

import androidx.room.Entity

@Entity(
    tableName = "mood_journals_emotion_options_cross_ref",
    primaryKeys = ["moodJournalId", "emotionOptionId"]
)
data class MoodJournalEntityEmotionOptionEntityCrossRef(
    val moodJournalId: Long,
    val emotionOptionId: Long
)