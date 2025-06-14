package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.MealJournal
import java.util.Date

@Entity(tableName = "meal_journals")
data class MealJournalEntity(
    @PrimaryKey(autoGenerate = true) val mealJournalId: Long,
    val mealTime: Date
)

fun MealJournalEntity.toModel() = MealJournal(
    id = mealJournalId,
    mealTime = mealTime,
    mealOptions = emptyList(),
    appetiteOptions = emptyList(),
    foodOptions = emptyList(),
    portionOptions = emptyList(),
    locationOptions = emptyList(),
    socialOptions = emptyList()
)

fun MealJournal.toEntity() = MealJournalEntity(
    mealJournalId = id ?: 0L,
    mealTime = mealTime
)