package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.DiaryEntity
import kotlinx.coroutines.flow.Flow

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
    fun getDiary(id: Long): Flow<DiaryEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiary(diary: DiaryEntity): Long

    @Update
    fun updateDiary(diary: DiaryEntity)

    @Delete
    fun deleteDiary(diary: DiaryEntity)
}