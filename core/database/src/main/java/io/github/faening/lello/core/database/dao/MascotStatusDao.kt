package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.mascot.MascotStatusEntity

@Dao
interface MascotStatusDao {
    @Query("SELECT * FROM mascot_status WHERE id = 1")
    suspend fun getStatus(): MascotStatusEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(status: MascotStatusEntity)
}
