package io.github.faening.lello.core.database.util

import androidx.room.TypeConverter
import io.github.faening.lello.core.database.model.MoodType

internal class JournalMoodTypeConverter {
    @TypeConverter
    fun fromMoodType(mood: MoodType): String = mood.name

    @TypeConverter
    fun toMoodType(name: String): MoodType = MoodType.valueOf(name)
}