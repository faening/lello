package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "mood_journal_entries")
data class MoodJournalEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Date,
    val mood: MoodType,
    val reflection: String? = null
)