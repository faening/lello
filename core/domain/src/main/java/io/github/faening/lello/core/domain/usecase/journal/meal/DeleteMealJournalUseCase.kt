package io.github.faening.lello.core.domain.usecase.journal.meal

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.model.journal.MealJournal
import javax.inject.Inject

class DeleteMealJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MealJournal>
) {
    suspend operator fun invoke(vararg entries: MealJournal) {
        entries.forEach { repository.delete(it) }
    }
}