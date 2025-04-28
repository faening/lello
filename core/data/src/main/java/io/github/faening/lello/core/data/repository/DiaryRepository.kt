package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.DiaryDao
import io.github.faening.lello.core.database.entity.toEntity
import io.github.faening.lello.core.database.entity.toModel
import io.github.faening.lello.core.model.diary.Diary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DiaryRepository @Inject constructor(
    private val diaryDao: DiaryDao
) : IDiaryRepository {

    override fun getDiaries(): Flow<List<Diary>> {
        return diaryDao.getDiaries().map { list -> list.map { it.toModel() } }
    }

    override fun getDiary(id: Long): Flow<Diary>? {
        return diaryDao.getDiary(id)?.map { it.toModel() }
    }

    override suspend fun insertDiary(diary: Diary) : Long {
        return diaryDao.insertDiary(diary.toEntity())
    }

    override suspend fun updateDiary(diary: Diary) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiary(id: Long) {
        TODO("Not yet implemented")
    }
}