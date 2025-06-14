package io.github.faening.lello.core.domain.usecase.journal

import io.github.faening.lello.core.domain.repository.JournalResources
import io.github.faening.lello.core.model.journal.SleepJournal
import javax.inject.Inject

class SleepJournalUseCase @Inject constructor(
    private val repository: JournalResources<SleepJournal>
) {
    suspend fun getAll(): List<SleepJournal> {
        return repository.getAll()
    }

    suspend fun getById(id: Long): SleepJournal? {
        return repository.getById(id)
    }

    suspend fun save(vararg entries: SleepJournal) {
        entries.forEach { repository.insert(it) }
    }

    suspend fun delete(vararg entries: SleepJournal) {
        entries.forEach { repository.delete(it) }
    }
}
