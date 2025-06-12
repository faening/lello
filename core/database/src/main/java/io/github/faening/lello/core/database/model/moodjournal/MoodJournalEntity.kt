package io.github.faening.lello.core.database.model.moodjournal

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.MoodType
import java.util.Date

@Entity(tableName = "mood_journals")
data class MoodJournalEntity(
    @PrimaryKey(autoGenerate = true) val moodJournalId: Long,
    val date: Date,
    val mood: MoodType,
    val reflection: String? = null
)