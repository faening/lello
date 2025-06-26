package io.github.faening.lello.core.database.model.journal.meal

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.github.faening.lello.core.database.model.option.AppetiteOptionEntity
import io.github.faening.lello.core.database.model.option.FoodOptionEntity
import io.github.faening.lello.core.database.model.option.LocationOptionEntity
import io.github.faening.lello.core.database.model.option.MealOptionEntity
import io.github.faening.lello.core.database.model.option.PortionOptionEntity
import io.github.faening.lello.core.database.model.option.SocialOptionEntity
import io.github.faening.lello.core.database.model.option.toModel
import io.github.faening.lello.core.model.journal.MealJournal

data class MealJournalEntityWithOptions(
    @Embedded val entry: MealJournalEntity,
    @Relation(
        parentColumn = "mealJournalId",
        entityColumn = "mealOptionId",
        associateBy = Junction(
            value = MealJournalEntityMealOptionEntityCrossRef::class,
            parentColumn = "mealJournalId",
            entityColumn = "mealOptionId"
        )
    )
    val mealOptions: List<MealOptionEntity>,
    @Relation(
        parentColumn = "mealJournalId",
        entityColumn = "appetiteOptionId",
        associateBy = Junction(
            value = MealJournalEntityAppetiteOptionEntityCrossRef::class,
            parentColumn = "mealJournalId",
            entityColumn = "appetiteOptionId"
        )
    )
    val appetiteOptions: List<AppetiteOptionEntity>,
    @Relation(
        parentColumn = "mealJournalId",
        entityColumn = "foodOptionId",
        associateBy = Junction(
            value = MealJournalEntityFoodOptionEntityCrossRef::class,
            parentColumn = "mealJournalId",
            entityColumn = "foodOptionId"
        )
    )
    val foodOptions: List<FoodOptionEntity>,
    @Relation(
        parentColumn = "mealJournalId",
        entityColumn = "portionOptionId",
        associateBy = Junction(
            value = MealJournalEntityPortionOptionEntityCrossRef::class,
            parentColumn = "mealJournalId",
            entityColumn = "portionOptionId"
        )
    )
    val portionOptions: List<PortionOptionEntity>,
    @Relation(
        parentColumn = "mealJournalId",
        entityColumn = "locationOptionId",
        associateBy = Junction(
            value = MealJournalEntityLocationOptionEntityCrossRef::class,
            parentColumn = "mealJournalId",
            entityColumn = "locationOptionId"
        )
    )
    val locationOptions: List<LocationOptionEntity>,
    @Relation(
        parentColumn = "mealJournalId",
        entityColumn = "socialOptionId",
        associateBy = Junction(
            value = MealJournalEntitySocialOptionEntityCrossRef::class,
            parentColumn = "mealJournalId",
            entityColumn = "socialOptionId"
        )
    )
    val socialOptions: List<SocialOptionEntity>,
)

fun MealJournalEntityWithOptions.toModel() = MealJournal(
    id = entry.mealJournalId,
    mealTime = entry.mealTime,
    createdAt = entry.createdAt,
    mealOptions = mealOptions.map { it.toModel() },
    appetiteOptions = appetiteOptions.map { it.toModel() },
    foodOptions = foodOptions.map { it.toModel() },
    portionOptions = portionOptions.map { it.toModel() },
    locationOptions = locationOptions.map { it.toModel() },
    socialOptions = socialOptions.map { it.toModel() },
)
