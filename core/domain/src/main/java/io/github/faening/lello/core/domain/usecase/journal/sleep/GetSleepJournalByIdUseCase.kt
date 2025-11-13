package io.github.faening.lello.core.domain.usecase.journal.sleep

import io.github.faening.lello.core.domain.repository.SleepJournalRepository
import io.github.faening.lello.core.model.journal.SleepJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSleepJournalByIdUseCase @Inject constructor(
    private val repository: SleepJournalRepository<SleepJournal>
) {
    operator fun invoke(id: Long): Flow<SleepJournal>? {
        return repository.getJournalById(id)
    }
}