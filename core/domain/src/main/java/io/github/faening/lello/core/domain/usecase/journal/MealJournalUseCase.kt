package io.github.faening.lello.core.domain.usecase.journal

import io.github.faening.lello.core.domain.repository.JournalResources
import io.github.faening.lello.core.model.journal.MealJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealJournalUseCase @Inject constructor(
    private val repository: JournalResources<MealJournal>,
) {
    fun getAll(): Flow<List<MealJournal>> {
        return repository.getAll()
    }

    fun getById(id: Long): Flow<MealJournal>? {
        return repository.getById(id)
    }

    suspend fun save(vararg entries: MealJournal) {
        entries.forEach { repository.insert(it) }
    }

    suspend fun delete(vararg entries: MealJournal) {
        entries.forEach { repository.delete(it) }
    }
}
