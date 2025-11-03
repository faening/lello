package io.github.faening.lello.core.domain.usecase.journal.sleep

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.model.journal.SleepJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSleepJournalUseCase @Inject constructor(
    private val repository: JournalRepository<SleepJournal>
) {
    operator fun invoke(): Flow<List<SleepJournal>> {
        return repository.getAll()
    }
}