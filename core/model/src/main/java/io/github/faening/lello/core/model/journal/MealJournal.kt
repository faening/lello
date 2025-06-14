package io.github.faening.lello.core.model.journal

import java.util.Date

data class MealJournal(
    val id: Long? = null,
    val mealTime: Date = Date(),
    val mealOptions: List<MealOption>,
    val appetiteOptions: List<AppetiteOption>,
    val foodOptions: List<FoodOption>,
    val portionOptions: List<PortionOption>,
    val locationOptions: List<LocationOption>,
    val socialOptions: List<SocialOption>,
)