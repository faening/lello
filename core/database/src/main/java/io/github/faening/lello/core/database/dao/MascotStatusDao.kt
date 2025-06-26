package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.mascot.MascotStatusEntity
import io.github.faening.lello.core.domain.repository.MascotStatusResource

@Dao
interface MascotStatusDao : MascotStatusResource<MascotStatusEntity> {
    @Query("SELECT * FROM mascot_status WHERE id = 1")
    override suspend fun getStatus(): MascotStatusEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertOrUpdate(status: MascotStatusEntity)
}
