package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.mascot.MascotVitalityHistoryEntity

@Dao
interface MascotVitalityHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: MascotVitalityHistoryEntity)

    @Query("SELECT * FROM mascot_vitality_history ORDER BY created_at DESC")
    suspend fun getHistory(): List<MascotVitalityHistoryEntity>

    @Query("SELECT * FROM mascot_vitality_history WHERE created_at BETWEEN :from AND :to ORDER BY created_at DESC")
    suspend fun getHistoryBetween(from: Long, to: Long): List<MascotVitalityHistoryEntity>
}
