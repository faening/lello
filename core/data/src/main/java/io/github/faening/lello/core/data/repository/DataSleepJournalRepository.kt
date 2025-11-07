package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SleepJournalDao
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepActivityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepQualityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepSensationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.toEntity
import io.github.faening.lello.core.database.model.journal.sleep.toModel
import io.github.faening.lello.core.domain.repository.SleepJournalRepository
import io.github.faening.lello.core.model.journal.SleepJournal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataSleepJournalRepository @Inject constructor(
    private val dao: SleepJournalDao
) : SleepJournalRepository<SleepJournal> {

    override fun getAllJournals(): Flow<List<SleepJournal>> {
        return dao.getAllJournals().map { list -> list.map { it.toModel() } }
    }

    override fun getJournalById(id: Long): Flow<SleepJournal>? {
        return dao.getJournalById(id)?.map { it.toModel() }
    }

    override suspend fun insert(item: SleepJournal): Long {
        val sleepJournalId = dao.insert(item.toEntity())

        if (item.sleepSensationOptions.isNotEmpty()) {
            linkSleepSensationOptions(sleepJournalId, item.sleepSensationOptions.map { it.id })
        }

        if (item.sleepQualityOptions.isNotEmpty()) {
            linkSleepQualityOptions(sleepJournalId, item.sleepQualityOptions.map { it.id })
        }

        if (item.sleepActivityOptions.isNotEmpty()) {
            linkSleepActivityOptions(sleepJournalId, item.sleepActivityOptions.map { it.id })
        }

        if (item.locationOptions.isNotEmpty()) {
            linkLocationOptions(sleepJournalId, item.locationOptions.map { it.id })
        }

        return sleepJournalId
    }

    override suspend fun linkSleepSensationOptions(sleepJournalId: Long, sleepSensationOptionIds: List<Long>) {
        if (sleepSensationOptionIds.isNotEmpty()) {
            dao.insertSleepSensationRefs(
                sleepSensationOptionIds.map { optionId ->
                    SleepJournalEntitySleepSensationOptionEntityCrossRef(sleepJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkSleepQualityOptions(sleepJournalId: Long, sleepQualityOptionIds: List<Long>) {
        if (sleepQualityOptionIds.isNotEmpty()) {
            dao.insertSleepQualityRefs(
                sleepQualityOptionIds.map { optionId ->
                    SleepJournalEntitySleepQualityOptionEntityCrossRef(sleepJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkSleepActivityOptions(sleepJournalId: Long, sleepActivityOptionIds: List<Long>) {
        if (sleepActivityOptionIds.isNotEmpty()) {
            dao.insertSleepActivityRefs(
                sleepActivityOptionIds.map { optionId ->
                    SleepJournalEntitySleepActivityOptionEntityCrossRef(sleepJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkLocationOptions(sleepJournalId: Long, locationOptionIds: List<Long>) {
        if (locationOptionIds.isNotEmpty()) {
            dao.insertLocationRefs(
                locationOptionIds.map { optionId ->
                    SleepJournalEntityLocationOptionEntityCrossRef(sleepJournalId, optionId)
                }
            )
        }
    }

    override suspend fun insert(items: List<SleepJournal>): List<Long> {
        return items.map { insert(it) }
    }
}