package io.github.faening.lello.core.domain.usecase.journal

import io.github.faening.lello.core.domain.repository.JournalResources
import io.github.faening.lello.core.model.journal.MoodJournal
import javax.inject.Inject

class MoodJournalUseCase @Inject constructor(
    private val repository: JournalResources<MoodJournal>
) {
    suspend fun getAll(): List<MoodJournal> {
        return repository.getAll()
    }

    suspend fun getById(id: Long): MoodJournal? {
        return repository.getById(id)
    }

    suspend fun save(vararg entries: MoodJournal) {
        entries.forEach { repository.insert(it) }
    }

    suspend fun delete(vararg entries: MoodJournal) {
        entries.forEach { repository.delete(it) }
    }
}
