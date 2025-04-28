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
) : ResourceRepository<Diary> {

    override fun getAll(): Flow<List<Diary>> {
        return diaryDao
            .getDiaries()
            .map { list -> list.map { it.toModel() } }
    }

    override fun getById(id: Long): Flow<Diary>? {
        return diaryDao
            .getDiary(id)
            ?.map { it.toModel() }
    }

    override suspend fun insert(entity: Diary): Long {
        return diaryDao.insertDiary(entity.toEntity())
    }

    override suspend fun update(entity: Diary) {
        diaryDao.updateDiary(entity.toEntity())
    }

    override suspend fun delete(id: Long) {
        diaryDao.getDiary(id)?.collect { diaryEntity ->
            diaryDao.deleteDiary(diaryEntity)
        }
    }
}