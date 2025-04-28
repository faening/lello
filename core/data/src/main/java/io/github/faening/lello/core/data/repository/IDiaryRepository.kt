package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.model.diary.Diary
import kotlinx.coroutines.flow.Flow

interface IDiaryRepository {
    fun getDiaries(): Flow<List<Diary>>
    fun getDiary(id: Long): Flow<Diary>?
    suspend fun insertDiary(diary: Diary): Long
    suspend fun updateDiary(diary: Diary)
    suspend fun deleteDiary(id: Long)
}