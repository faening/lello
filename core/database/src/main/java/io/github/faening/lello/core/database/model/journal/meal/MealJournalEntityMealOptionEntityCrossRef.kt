package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "meal_journals_meal_options_cross_ref",
    primaryKeys = ["mealJournalId", "mealOptionId"],
    indices = [Index(value = ["mealOptionId"])]
)
data class MealJournalEntityMealOptionEntityCrossRef(
    val mealJournalId: Long,
    val mealOptionId: Long,
)
