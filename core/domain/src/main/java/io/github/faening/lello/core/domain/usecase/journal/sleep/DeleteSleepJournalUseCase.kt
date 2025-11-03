package io.github.faening.lello.core.domain.usecase.journal.sleep

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.model.journal.SleepJournal
import javax.inject.Inject

class DeleteSleepJournalUseCase @Inject constructor(
    private val repository: JournalRepository<SleepJournal>
) {
    suspend operator fun invoke(vararg entries: SleepJournal) {
        entries.forEach { repository.delete(it) }
    }
}