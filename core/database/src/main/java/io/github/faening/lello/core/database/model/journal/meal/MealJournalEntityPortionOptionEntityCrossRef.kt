package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "meal_journals_portion_options_cross_ref",
    primaryKeys = ["mealJournalId", "portionOptionId"],
    indices = [Index(value = ["portionOptionId"])]
)
data class MealJournalEntityPortionOptionEntityCrossRef(
    val mealJournalId: Long,
    val portionOptionId: Long,
)
