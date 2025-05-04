package io.github.faening.lello.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.dao.JournalDao
import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.model.ClimateOptionEntity
import io.github.faening.lello.core.database.model.EmotionOptionEntity
import io.github.faening.lello.core.database.model.JournalEntity
import io.github.faening.lello.core.database.model.LocationOptionEntity
import io.github.faening.lello.core.database.util.InstantConverters

@Database(
    entities = [
        ClimateOptionEntity::class,
        EmotionOptionEntity::class,
        JournalEntity::class,
        LocationOptionEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    InstantConverters::class,
)
abstract class LelloDatabase : RoomDatabase() {
    abstract fun climateOptionDao(): ClimateOptionDao
    abstract fun emotionOptionDao(): EmotionOptionDao
    abstract fun JournalDao(): JournalDao
    abstract fun locationOptionDao() : LocationOptionDao
}