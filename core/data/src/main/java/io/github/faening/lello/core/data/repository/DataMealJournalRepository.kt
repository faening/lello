package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MealJournalDao
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityAppetiteOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityFoodOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityMealOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityPortionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.toEntity
import io.github.faening.lello.core.database.model.journal.meal.toModel
import io.github.faening.lello.core.domain.repository.MealJournalRepository
import io.github.faening.lello.core.model.journal.MealJournal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataMealJournalRepository @Inject constructor(
    private val dao: MealJournalDao
) : MealJournalRepository<MealJournal> {

    override fun getAllJournals(): Flow<List<MealJournal>> {
        return dao.getAllJournals().map { list -> list.map { it.toModel() } }
    }

    override fun getJournalById(id: Long): Flow<MealJournal>? {
        return dao.getJournalById(id)?.map { it.toModel() }
    }

    override suspend fun insert(item: MealJournal): Long {
        val mealJournalId = dao.insert(item.toEntity())

        if (item.mealOptions.isNotEmpty()) {
            linkMealOptions(mealJournalId, item.mealOptions.map { it.id })
        }

        if (item.appetiteOptions.isNotEmpty()) {
            linkAppetiteOptions(mealJournalId, item.appetiteOptions.map { it.id })
        }

        if (item.foodOptions.isNotEmpty()) {
            linkFoodOptions(mealJournalId, item.foodOptions.map { it.id })
        }

        if (item.portionOptions.isNotEmpty()) {
            linkPortionOptions(mealJournalId, item.portionOptions.map { it.id })
        }

        if (item.locationOptions.isNotEmpty()) {
            linkLocationOptions(mealJournalId, item.locationOptions.map { it.id })
        }

        if (item.socialOptions.isNotEmpty()) {
            linkSocialOptions(mealJournalId, item.socialOptions.map { it.id })
        }

        return mealJournalId
    }

    override suspend fun linkMealOptions(mealJournalId: Long, mealOptionIds: List<Long>) {
        if (mealOptionIds.isNotEmpty()) {
            dao.insertMealRefs(
                mealOptionIds.map { optionId ->
                    MealJournalEntityMealOptionEntityCrossRef(mealJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkAppetiteOptions(mealJournalId: Long, appetiteOptionIds: List<Long>) {
        if (appetiteOptionIds.isNotEmpty()) {
            dao.insertAppetiteRefs(
                appetiteOptionIds.map { optionId ->
                    MealJournalEntityAppetiteOptionEntityCrossRef(mealJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkFoodOptions(mealJournalId: Long, foodOptionIds: List<Long>) {
        if (foodOptionIds.isNotEmpty()) {
            dao.insertFoodRefs(
                foodOptionIds.map { optionId ->
                    MealJournalEntityFoodOptionEntityCrossRef(mealJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkPortionOptions(mealJournalId: Long, portionOptionIds: List<Long>) {
        if (portionOptionIds.isNotEmpty()) {
            dao.insertPortionRefs(
                portionOptionIds.map { optionId ->
                    MealJournalEntityPortionOptionEntityCrossRef(mealJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkLocationOptions(mealJournalId: Long, locationOptionIds: List<Long>) {
        if (locationOptionIds.isNotEmpty()) {
            dao.insertLocationRefs(
                locationOptionIds.map { optionId ->
                    MealJournalEntityLocationOptionEntityCrossRef(mealJournalId, optionId)
                }
            )
        }
    }

    override suspend fun linkSocialOptions(mealJournalId: Long, socialOptionIds: List<Long>) {
        if (socialOptionIds.isNotEmpty()) {
            dao.insertSocialRefs(
                socialOptionIds.map { optionId ->
                    MealJournalEntitySocialOptionEntityCrossRef(mealJournalId, optionId)
                }
            )
        }
    }

    override suspend fun insert(items: List<MealJournal>): List<Long> {
        return items.map { insert(it) }
    }

    override suspend fun delete(item: MealJournal) {
        dao.delete(item.toEntity())
    }
}