package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "meal_journals_food_options_cross_ref",
    primaryKeys = ["mealJournalId", "foodOptionId"],
    indices = [Index(value = ["foodOptionId"])]
)
data class MealJournalEntityFoodOptionEntityCrossRef(
    val mealJournalId: Long,
    val foodOptionId: Long,
)
