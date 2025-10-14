package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.MealJournal

object MealJournalMock {
    val list = listOf(
        MealJournal(
            id = 1,
            mealTime = System.currentTimeMillis(),
            mealOptions = MealOptionMock.list.take(1),
            appetiteOptions = AppetiteOptionMock.list.take(1),
            foodOptions = FoodOptionMock.list.take(1),
            portionOptions = PortionOptionMock.list.take(1),
            locationOptions = LocationOptionMock.list.take(1),
            socialOptions = SocialOptionMock.list.take(1),
            createdAt = System.currentTimeMillis()
        ),
        MealJournal(
            id = 2,
            mealTime = System.currentTimeMillis(),
            mealOptions = MealOptionMock.list.take(2),
            appetiteOptions = AppetiteOptionMock.list.take(2),
            foodOptions = FoodOptionMock.list.take(2),
            portionOptions = PortionOptionMock.list.take(2),
            locationOptions = LocationOptionMock.list.take(2),
            socialOptions = SocialOptionMock.list.take(2),
            createdAt = System.currentTimeMillis()
        ),
        MealJournal(
            id = 3,
            mealTime = System.currentTimeMillis(),
            mealOptions = MealOptionMock.list.take(3),
            appetiteOptions = AppetiteOptionMock.list.take(3),
            foodOptions = FoodOptionMock.list.take(3),
            portionOptions = PortionOptionMock.list.take(3),
            locationOptions = LocationOptionMock.list.take(3),
            socialOptions = SocialOptionMock.list.take(3),
            createdAt = System.currentTimeMillis()
        ),
        MealJournal(
            id = 4,
            mealTime = System.currentTimeMillis(),
            mealOptions = MealOptionMock.list.take(4),
            appetiteOptions = AppetiteOptionMock.list.take(4),
            foodOptions = FoodOptionMock.list.take(4),
            portionOptions = PortionOptionMock.list.take(4),
            locationOptions = LocationOptionMock.list.take(4),
            socialOptions = SocialOptionMock.list.take(4),
            createdAt = System.currentTimeMillis()
        ),
        MealJournal(
            id = 5,
            mealTime = System.currentTimeMillis(),
            mealOptions = MealOptionMock.list.take(5),
            appetiteOptions = AppetiteOptionMock.list.take(5),
            foodOptions = FoodOptionMock.list.take(5),
            portionOptions = PortionOptionMock.list.take(5),
            locationOptions = LocationOptionMock.list.take(5),
            socialOptions = SocialOptionMock.list.take(5),
            createdAt = System.currentTimeMillis()
        )
    )
}
