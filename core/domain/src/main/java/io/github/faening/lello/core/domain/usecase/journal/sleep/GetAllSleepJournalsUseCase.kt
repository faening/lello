package io.github.faening.lello.core.domain.usecase.journal.sleep

import io.github.faening.lello.core.domain.repository.SleepJournalRepository
import io.github.faening.lello.core.model.journal.SleepJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSleepJournalsUseCase @Inject constructor(
    private val repository: SleepJournalRepository<SleepJournal>
) {
    operator fun invoke(): Flow<List<SleepJournal>> {
        return repository.getAllJournals()
    }
}