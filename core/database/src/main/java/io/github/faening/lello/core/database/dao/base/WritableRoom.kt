package io.github.faening.lello.core.database.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Suppress("unused")
interface WritableRoom<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg entities: T)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg entities: T)

    @Delete
    fun delete(entity: T)
}