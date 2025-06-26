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
import io.github.faening.lello.core.domain.repository.JournalResources
import io.github.faening.lello.core.model.journal.MealJournal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealJournalRepository @Inject constructor(
    private val dao: MealJournalDao
) : JournalResources<MealJournal> {

    override fun getAll(): Flow<List<MealJournal>> {
        return dao
            .getAll()
            .map { list -> list.map { it.toModel() } }
    }

    override fun getById(id: Long): Flow<MealJournal>? {
        return dao
            .getByIdWithOptions(id)
            ?.map { it.toModel() }
    }

    override suspend fun insert(entry: MealJournal): Long {
        val mealJournalId = dao.insert(entry.toEntity())

        if (entry.mealOptions.isNotEmpty()) {
            dao.insertMealRefs(
                entry.mealOptions.map { option ->
                    MealJournalEntityMealOptionEntityCrossRef(mealJournalId, option.id)
                }
            )
        }

        if (entry.appetiteOptions.isNotEmpty()) {
            dao.insertAppetiteRefs(
                entry.appetiteOptions.map { option ->
                    MealJournalEntityAppetiteOptionEntityCrossRef(mealJournalId, option.id)
                }
            )
        }

        if (entry.foodOptions.isNotEmpty()) {
            dao.insertFoodRefs(
                entry.foodOptions.map { option ->
                    MealJournalEntityFoodOptionEntityCrossRef(mealJournalId, option.id)
                }
            )
        }

        if (entry.portionOptions.isNotEmpty()) {
            dao.insertPortionRefs(
                entry.portionOptions.map { option ->
                    MealJournalEntityPortionOptionEntityCrossRef(mealJournalId, option.id)
                }
            )
        }

        if (entry.locationOptions.isNotEmpty()) {
            dao.insertLocationRefs(
                entry.locationOptions.map { option ->
                    MealJournalEntityLocationOptionEntityCrossRef(mealJournalId, option.id)
                }
            )
        }

        if (entry.socialOptions.isNotEmpty()) {
            dao.insertSocialRefs(
                entry.socialOptions.map { option ->
                    MealJournalEntitySocialOptionEntityCrossRef(mealJournalId, option.id)
                }
            )
        }

        return mealJournalId
    }

    override suspend fun delete(id: MealJournal) {
        dao.delete(id.toEntity())
    }
}
