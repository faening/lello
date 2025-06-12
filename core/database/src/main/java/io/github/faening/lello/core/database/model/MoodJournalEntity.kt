package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.MoodType
import java.util.Date

@Entity(tableName = "mood_journals")
data class MoodJournalEntity(
    @PrimaryKey(autoGenerate = true) val moodJournalId: Long = 0L,
    val date: Date,
    val mood: MoodType,
    val reflection: String? = null
)