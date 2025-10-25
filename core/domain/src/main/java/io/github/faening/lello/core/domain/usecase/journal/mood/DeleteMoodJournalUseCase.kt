package io.github.faening.lello.core.domain.usecase.journal.mood

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.model.journal.MoodJournal
import javax.inject.Inject

class DeleteMoodJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MoodJournal>
) {
    suspend operator fun invoke(vararg entries: MoodJournal) {
        entries.forEach { repository.delete(it) }
    }
}