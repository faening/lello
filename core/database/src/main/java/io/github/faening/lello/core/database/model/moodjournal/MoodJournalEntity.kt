package io.github.faening.lello.core.database.model.moodjournal

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.MoodType
import java.util.Date

@Entity(tableName = "mood_journals")
data class MoodJournalEntity(
    @PrimaryKey(autoGenerate = true) val moodJournalId: Long,
    val date: Date,
    val mood: MoodType,
    val reflection: String? = null
)

fun MoodJournalEntity.toModel(): MoodJournal {
    return MoodJournal(
        id = moodJournalId,
        date = date,
        mood = mood,
        reflection = reflection,
        emotionOptions = emptyList(),
        climateOptions = emptyList(),
        locationOptions = emptyList(),
        socialOptions = emptyList(),
        healthOptions = emptyList()
    )
}

fun MoodJournal.toEntity(): MoodJournalEntity {
    return MoodJournalEntity(
        moodJournalId = id ?: 0L,
        date = date,
        mood = mood,
        reflection = reflection
    )
}