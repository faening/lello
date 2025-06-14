package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "meal_journals_social_options_cross_ref",
    primaryKeys = ["mealJournalId", "socialOptionId"],
    indices = [Index(value = ["socialOptionId"])]
)
data class MealJournalEntitySocialOptionEntityCrossRef(
    val mealJournalId: Long,
    val socialOptionId: Long,
)
