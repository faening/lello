package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.entity.DiaryEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface DiaryDao {

    @Transaction
    @Query(
        value = """
            SELECT * FROM diaries
        """
    )
    fun getDiaries(): Flow<List<DiaryEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM diaries
            WHERE id = :id
        """
    )
    fun getDiary(id: UUID): DiaryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: DiaryEntity): Long

    @Update
    suspend fun updateDiary(diary: DiaryEntity)

    @Delete
    suspend fun deleteDiary(diary: DiaryEntity)
}