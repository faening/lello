package io.github.faening.lello.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.dao.MoodJournalEntryDao
import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.database.model.ClimateOptionEntity
import io.github.faening.lello.core.database.model.EmotionOptionEntity
import io.github.faening.lello.core.database.model.HealthOptionEntity
import io.github.faening.lello.core.database.model.JournalCategoryEntity
import io.github.faening.lello.core.database.model.LocationOptionEntity
import io.github.faening.lello.core.database.model.MoodJournalEntryEntity
import io.github.faening.lello.core.database.model.MoodJournalEntryClimateOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntryEmotionOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntryHealthOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntryLocationOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntrySocialOptionCrossRef
import io.github.faening.lello.core.database.model.SocialOptionEntity
import io.github.faening.lello.core.database.util.DateConverter
import io.github.faening.lello.core.database.util.InstantConverters
import io.github.faening.lello.core.database.util.JournalMoodTypeConverter

@Database(
    entities = [
        JournalCategoryEntity::class,
        ClimateOptionEntity::class,
        EmotionOptionEntity::class,
        HealthOptionEntity::class,
        LocationOptionEntity::class,
        SocialOptionEntity::class,
        MoodJournalEntryEntity::class,
        MoodJournalEntryEmotionOptionCrossRef::class,
        MoodJournalEntryClimateOptionCrossRef::class,
        MoodJournalEntryLocationOptionCrossRef::class,
        MoodJournalEntrySocialOptionCrossRef::class,
        MoodJournalEntryHealthOptionCrossRef::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    InstantConverters::class,
    JournalMoodTypeConverter::class,
    DateConverter::class
)
abstract class LelloDatabase : RoomDatabase() {
    abstract fun journalCategoryDao(): JournalCategoryDao
    abstract fun climateOptionDao(): ClimateOptionDao
    abstract fun emotionOptionDao(): EmotionOptionDao
    abstract fun healthOptionDao(): HealthOptionDao
    abstract fun locationOptionDao() : LocationOptionDao
    abstract fun socialOptionDao() : SocialOptionDao
    abstract fun moodJournalEntryDao(): MoodJournalEntryDao
}