package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.mascot.MascotVitalityHistoryEntity
import io.github.faening.lello.core.domain.repository.MascotVitalityResource

@Dao
interface MascotVitalityHistoryDao : MascotVitalityResource<MascotVitalityHistoryEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(history: MascotVitalityHistoryEntity)

    @Query("SELECT * FROM mascot_vitality_history ORDER BY created_at DESC")
    override suspend fun getHistory(): List<MascotVitalityHistoryEntity>

    @Query("SELECT * FROM mascot_vitality_history WHERE created_at BETWEEN :from AND :to ORDER BY created_at DESC")
    override suspend fun getHistoryBetween(from: Long, to: Long): List<MascotVitalityHistoryEntity>
}
