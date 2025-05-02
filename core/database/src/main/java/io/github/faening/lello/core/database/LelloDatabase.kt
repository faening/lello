package io.github.faening.lello.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.faening.lello.core.database.dao.JournalDao
import io.github.faening.lello.core.database.model.JournalEntity
import io.github.faening.lello.core.database.util.InstantConverters
import io.github.faening.lello.core.database.util.UUIDCOnverters

@Database(
    entities = [
        JournalEntity::class
    ],
    version = 1,
    autoMigrations = [],
    exportSchema = true
)
@TypeConverters(
    InstantConverters::class,
)
abstract class LelloDatabase : RoomDatabase() {
    abstract fun JournalDao(): JournalDao
}