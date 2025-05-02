package io.github.faening.lello.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.faening.lello.core.database.dao.DiaryDao
import io.github.faening.lello.core.database.model.DiaryEntity
import io.github.faening.lello.core.database.util.InstantConverters
import io.github.faening.lello.core.database.util.UUIDCOnverters

@Database(
    entities = [
        DiaryEntity::class
    ],
    version = 1,
    autoMigrations = [

    ],
    exportSchema = true
)
@TypeConverters(
    InstantConverters::class,
    UUIDCOnverters::class
)
abstract class LelloDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao

}