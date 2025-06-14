package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "meal_journals_location_options_cross_ref",
    primaryKeys = ["mealJournalId", "locationOptionId"],
    indices = [Index(value = ["locationOptionId"])]
)
data class MealJournalEntityLocationOptionEntityCrossRef(
    val mealJournalId: Long,
    val locationOptionId: Long,
)
