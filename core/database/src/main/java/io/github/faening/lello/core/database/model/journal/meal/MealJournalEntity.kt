package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.MealJournal

@Entity(tableName = "meal_journals")
data class MealJournalEntity(
    @PrimaryKey(autoGenerate = true) val mealJournalId: Long,
    @ColumnInfo(name = "meal_time") val mealTime: Long,
    @ColumnInfo(name = "created_at") val createdAt: Long,
)

fun MealJournalEntity.toModel() = MealJournal(
    id = mealJournalId,
    mealTime = mealTime,
    createdAt = createdAt,
    mealOptions = emptyList(),
    appetiteOptions = emptyList(),
    foodOptions = emptyList(),
    portionOptions = emptyList(),
    locationOptions = emptyList(),
    socialOptions = emptyList()
)

fun MealJournal.toEntity() = MealJournalEntity(
    mealJournalId = id ?: 0L,
    mealTime = mealTime,
    createdAt = createdAt
)