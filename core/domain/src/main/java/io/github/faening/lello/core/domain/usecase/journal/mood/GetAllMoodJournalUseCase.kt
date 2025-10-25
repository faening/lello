package io.github.faening.lello.core.domain.usecase.journal.mood

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.model.journal.MoodJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoodJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MoodJournal>
) {
    operator fun invoke(): Flow<List<MoodJournal>> {
        return repository.getAll()
    }
}