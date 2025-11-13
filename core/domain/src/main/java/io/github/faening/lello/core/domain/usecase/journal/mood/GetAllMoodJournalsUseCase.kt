package io.github.faening.lello.core.domain.usecase.journal.mood

import io.github.faening.lello.core.domain.repository.MoodJournalRepository
import io.github.faening.lello.core.model.journal.MoodJournal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoodJournalsUseCase @Inject constructor(
    private val repository: MoodJournalRepository<MoodJournal>
) {
    operator fun invoke(): Flow<List<MoodJournal>> {
        return repository.getAllJournals()
    }
}