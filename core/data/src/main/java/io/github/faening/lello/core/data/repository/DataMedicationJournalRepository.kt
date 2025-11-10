package io.github.faening.lello.core.data.repository

import android.util.Log
import io.github.faening.lello.core.database.dao.MedicationJournalDao
import io.github.faening.lello.core.database.model.journal.medication.toEntity
import io.github.faening.lello.core.database.model.journal.medication.toModel
import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.model.journal.MedicationJournal
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataMedicationJournalRepository @Inject constructor(
    private val dao: MedicationJournalDao
) : MedicationJournalRepository<MedicationJournal> {

    override fun getAllJournals(): Flow<List<MedicationJournal>> {
        return dao.getAllJournals().map { list -> list.map { it.toModel() } }
    }

    override fun getJournalsByDay(dayStart: Long, dayEnd: Long): Flow<List<MedicationJournal>> {
        return dao.getJournalsByDay(dayStart, dayEnd).map { list -> list.map { it.toModel() } }
    }

    override fun getJournalsByTakenStatus(taken: Boolean): Flow<List<MedicationJournal>> {
        return dao.getJournalsByTakenStatus(taken).map { list -> list.map { it.toModel() } }
    }

    override fun getJournalsByMedication(medicationId: Long): Flow<List<MedicationJournal>> {
        return dao.getJournalsByMedication(medicationId).map { list -> list.map { it.toModel() } }
    }

    override suspend fun getRegisteredDosageIdsForToday(medicationId: Long, currentDay: Long): List<Long> {
        val currentDay = System.currentTimeMillis()
        return dao.getRegisteredDosageIdsForToday(medicationId, currentDay)
    }

    override suspend fun insert(item: MedicationJournal): Long {
        Log.d("Test", "insert: $item")
        return dao.insert(item.toEntity())
    }

    override suspend fun insert(items: List<MedicationJournal>): List<Long> {
        return dao.insert(items.map { it.toEntity() })
    }
}