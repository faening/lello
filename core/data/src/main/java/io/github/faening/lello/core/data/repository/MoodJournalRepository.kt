package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MoodJournalDao
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntity
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.toModel
import io.github.faening.lello.core.database.model.journal.mood.toEntity
import io.github.faening.lello.core.domain.repository.MoodJournalResources
import io.github.faening.lello.core.model.journal.MoodJournal
import javax.inject.Inject

class MoodJournalRepository @Inject constructor(
    private val dao: MoodJournalDao
) : MoodJournalResources<MoodJournal> {

    override suspend fun insert(entry: MoodJournal) : Long {
        val moodJournalId = dao.insert(
            MoodJournalEntity(
                moodJournalId = 0L,
                date = entry.date,
                mood = entry.mood,
                reflection = entry.reflection ?: "",
            )
        )

        if (entry.emotionOptions.isNotEmpty()) {
            dao.insertEmotionRefs(
                entry.emotionOptions.map {
                    MoodJournalEntityEmotionOptionEntityCrossRef(moodJournalId, it.id)
                }
            )
        }

        if (entry.climateOptions.isNotEmpty()) {
            dao.insertClimateRefs(
                entry.climateOptions.map {
                    MoodJournalEntityClimateOptionEntityCrossRef(moodJournalId, it.id)
                }
            )
        }

        if (entry.locationOptions.isNotEmpty()) {
            dao.insertLocationRefs(
                entry.locationOptions.map {
                    MoodJournalEntityLocationOptionEntityCrossRef(moodJournalId, it.id)
                }
            )
        }

        if (entry.socialOptions.isNotEmpty()) {
            dao.insertSocialRefs(
                entry.socialOptions.map {
                    MoodJournalEntitySocialOptionEntityCrossRef(moodJournalId, it.id)
                }
            )
        }

        if (entry.healthOptions.isNotEmpty()) {
            dao.insertHealthRefs(
                entry.healthOptions.map {
                    MoodJournalEntityHealthOptionEntityCrossRef(moodJournalId, it.id)
                }
            )
        }

        return moodJournalId
    }

    override suspend fun getAll(): List<MoodJournal> {
        return dao.getAll().map { it.toModel() }
    }

    override suspend fun getById(id: Long): MoodJournal? {
        return dao.getByIdWithOptions(id)?.toModel()
    }

    override suspend fun delete(id: MoodJournal) {
        dao.delete(id.toEntity())
    }
}