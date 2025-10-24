package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SleepJournalDao
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepActivityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepQualityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepSensationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.toEntity
import io.github.faening.lello.core.database.model.journal.sleep.toModel
import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.model.journal.SleepJournal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataJournalSleepRepository @Inject constructor(
    private val dao: SleepJournalDao
) : JournalRepository<SleepJournal> {

    override fun getAll(): Flow<List<SleepJournal>> {
        return dao.getAllWithOptions().map { list -> list.map { it.toModel() } }
    }

    override fun getById(id: Long): Flow<SleepJournal>? {
        return dao.getByIdWithOptions(id)?.map { it.toModel() }
    }

    override suspend fun insert(entry: SleepJournal): Long {
        val sleepJournalId = dao.insert(entry.toEntity())

        if (entry.sleepSensationOptions.isNotEmpty()) {
            dao.insertSleepSensationRefs(
                entry.sleepSensationOptions.map {
                    SleepJournalEntitySleepSensationOptionEntityCrossRef(sleepJournalId, it.id)
                }
            )
        }

        if (entry.sleepQualityOptions.isNotEmpty()) {
            dao.insertSleepQualityRefs(
                entry.sleepQualityOptions.map {
                    SleepJournalEntitySleepQualityOptionEntityCrossRef(sleepJournalId, it.id)
                }
            )
        }

        if (entry.sleepActivityOptions.isNotEmpty()) {
            dao.insertSleepActivityRefs(
                entry.sleepActivityOptions.map {
                    SleepJournalEntitySleepActivityOptionEntityCrossRef(sleepJournalId, it.id)
                }
            )
        }

        if (entry.locationOptions.isNotEmpty()) {
            dao.insertLocationRefs(
                entry.locationOptions.map {
                    SleepJournalEntityLocationOptionEntityCrossRef(sleepJournalId, it.id)
                }
            )
        }

        return sleepJournalId
    }

    override suspend fun delete(id: SleepJournal) {
        dao.delete(id.toEntity())
    }
}
