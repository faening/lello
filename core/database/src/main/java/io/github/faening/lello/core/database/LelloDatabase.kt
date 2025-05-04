package io.github.faening.lello.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.faening.lello.core.database.dao.EmotionDao
import io.github.faening.lello.core.database.dao.JournalDao
import io.github.faening.lello.core.database.model.EmotionEntity
import io.github.faening.lello.core.database.model.JournalEntity
import io.github.faening.lello.core.database.util.InstantConverters

@Database(
    entities = [
        JournalEntity::class,
        EmotionEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    InstantConverters::class,
)
abstract class LelloDatabase : RoomDatabase() {
    abstract fun JournalDao(): JournalDao
    abstract fun EmotionDao(): EmotionDao
}