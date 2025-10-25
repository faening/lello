package io.github.faening.lello.core.domain.usecase.journal.meal

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.model.journal.MealJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMealJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MealJournal>
) {
    operator fun invoke(): Flow<List<MealJournal>> {
        return repository.getAll()
    }
}