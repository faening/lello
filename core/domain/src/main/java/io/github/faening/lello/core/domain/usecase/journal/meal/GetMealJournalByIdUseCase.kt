package io.github.faening.lello.core.domain.usecase.journal.meal

import io.github.faening.lello.core.domain.repository.MealJournalRepository
import io.github.faening.lello.core.model.journal.MealJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealJournalByIdUseCase @Inject constructor(
    private val repository: MealJournalRepository<MealJournal>
) {
    operator fun invoke(id: Long): Flow<MealJournal>? {
        return repository.getJournalById(id)
    }
}