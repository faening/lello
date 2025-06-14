package io.github.faening.lello.core.model.journal

import io.github.faening.lello.core.model.option.AppetiteOption
import io.github.faening.lello.core.model.option.FoodOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.MealOption
import io.github.faening.lello.core.model.option.PortionOption
import io.github.faening.lello.core.model.option.SocialOption
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