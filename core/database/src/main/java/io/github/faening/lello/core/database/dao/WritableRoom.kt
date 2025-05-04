package io.github.faening.lello.core.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Suppress("unused")
interface WritableRoom<T> {

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    fun insert(vararg entities: T)

    @Update(onConflict = OnConflictStrategy.Companion.IGNORE)
    fun update(vararg entities: T)

    @Delete
    fun delete(entity: T)
}