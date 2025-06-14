package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "meal_journals_appetite_options_cross_ref",
    primaryKeys = ["mealJournalId", "appetiteOptionId"],
    indices = [Index(value = ["appetiteOptionId"])]
)
data class MealJournalEntityAppetiteOptionEntityCrossRef(
    val mealJournalId: Long,
    val appetiteOptionId: Long,
)
