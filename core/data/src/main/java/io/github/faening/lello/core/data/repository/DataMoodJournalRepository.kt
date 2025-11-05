package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MoodJournalDao
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntity
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.toEntity
import io.github.faening.lello.core.database.model.journal.mood.toModel
import io.github.faening.lello.core.domain.repository.MoodJournalRepository
import io.github.faening.lello.core.model.journal.MoodJournal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataMoodJournalRepository @Inject constructor(
    private val dao: MoodJournalDao
) : MoodJournalRepository<MoodJournal> {

    override fun getAllJournals(): Flow<List<MoodJournal>> {
        return dao.getAllJournals().map { list -> list.map { it.toModel() } }
    }

    override fun getJournalById(id: Long): Flow<MoodJournal>? {
        return dao.getJournalById(id)?.map { it.toModel() }
    }

    override fun getJournalsByMood(mood: String): Flow<List<MoodJournal>> {
        return dao.getJournalsByMood(mood).map { list -> list.map { it.toModel() } }
    }

    override suspend fun insert(item: MoodJournal): Long {
        val moodJournalId = dao.insert(
            MoodJournalEntity(
                moodJournalId = 0L,
                createdAt = item.createdAt,
                mood = item.mood,
                reflection = item.reflection ?: "",
            )
        )

        linkEmotionOptions(moodJournalId, item.emotionOptions.map { it.id })
        linkClimateOptions(moodJournalId, item.climateOptions.map { it.id })
        linkLocationOptions(moodJournalId, item.locationOptions.map { it.id })
        linkSocialOptions(moodJournalId, item.socialOptions.map { it.id })
        linkHealthOptions(moodJournalId, item.healthOptions.map { it.id })

        return moodJournalId
    }

    override suspend fun linkEmotionOptions(moodJournalId: Long, emotionOptionIds: List<Long>) {
        if (emotionOptionIds.isNotEmpty()) {
            dao.insertEmotionRefs(
                emotionOptionIds.map {
                    MoodJournalEntityEmotionOptionEntityCrossRef(moodJournalId, it)
                }
            )
        }
    }

    override suspend fun linkClimateOptions(moodJournalId: Long, climateOptionIds: List<Long>) {
        if (climateOptionIds.isNotEmpty()) {
            dao.insertClimateRefs(
                climateOptionIds.map {
                    MoodJournalEntityClimateOptionEntityCrossRef(moodJournalId, it)
                }
            )
        }
    }

    override suspend fun linkLocationOptions(moodJournalId: Long, locationOptionIds: List<Long>) {
        if (locationOptionIds.isNotEmpty()) {
            dao.insertLocationRefs(
                locationOptionIds.map {
                    MoodJournalEntityLocationOptionEntityCrossRef(moodJournalId, it)
                }
            )
        }
    }

    override suspend fun linkSocialOptions(moodJournalId: Long, socialOptionIds: List<Long>) {
        if (socialOptionIds.isNotEmpty()) {
            dao.insertSocialRefs(
                socialOptionIds.map {
                    MoodJournalEntitySocialOptionEntityCrossRef(moodJournalId, it)
                }
            )
        }
    }

    override suspend fun linkHealthOptions(moodJournalId: Long, healthOptionIds: List<Long>) {
        if (healthOptionIds.isNotEmpty()) {
            dao.insertHealthRefs(
                healthOptionIds.map {
                    MoodJournalEntityHealthOptionEntityCrossRef(moodJournalId, it)
                }
            )
        }
    }

    override suspend fun insert(items: List<MoodJournal>): List<Long> {
        return items.map { insert(it) }
    }

    override suspend fun delete(item: MoodJournal) {
        dao.delete(item.toEntity())
    }
}