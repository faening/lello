package io.github.faening.lello.core.database.model.journal.mood

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.MoodType

@Entity(tableName = "mood_journals")
data class MoodJournalEntity(
    @PrimaryKey(autoGenerate = true) val moodJournalId: Long,
    val mood: MoodType,
    val reflection: String? = null,
    @ColumnInfo(name = "created_at") val createdAt: Long,
)

fun MoodJournalEntity.toModel(): MoodJournal {
    return MoodJournal(
        id = moodJournalId,
        mood = mood,
        reflection = reflection,
        emotionOptions = emptyList(),
        climateOptions = emptyList(),
        locationOptions = emptyList(),
        socialOptions = emptyList(),
        healthOptions = emptyList(),
        createdAt = createdAt,
    )
}

fun MoodJournal.toEntity(): MoodJournalEntity {
    return MoodJournalEntity(
        moodJournalId = id ?: 0L,
        mood = mood,
        reflection = reflection,
        createdAt = createdAt,
    )
}