package io.github.faening.lello.core.domain.service

import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.reward.RewardPoints
import javax.inject.Inject

class RewardCalculatorService @Inject constructor() {

    fun calculateForMealJournal(mealJournal: MealJournal): Int {
        val rewardPoints = RewardPoints.MEAL_JOURNAL
        var reward = rewardPoints.basePoints

        if (mealJournal.mealTime > 0) reward += rewardPoints.extraPoints
        if (mealJournal.foodOptions.isNotEmpty()) reward += rewardPoints.extraPoints
        if (mealJournal.portionOptions.isNotEmpty()) reward += rewardPoints.extraPoints
        if (mealJournal.locationOptions.isNotEmpty()) reward += rewardPoints.extraPoints
        if (mealJournal.socialOptions.isNotEmpty()) reward += rewardPoints.extraPoints

        return reward
    }

    fun calculateForMoodJournal(moodJournal: MoodJournal): Int {
        val rewardPoints = RewardPoints.MOOD_JOURNAL
        var reward = rewardPoints.basePoints

        if (moodJournal.climateOptions.isNotEmpty()) reward += rewardPoints.extraPoints
        if (moodJournal.locationOptions.isNotEmpty()) reward += rewardPoints.extraPoints
        if (moodJournal.socialOptions.isNotEmpty()) reward += rewardPoints.extraPoints
        if (moodJournal.healthOptions.isNotEmpty()) reward += rewardPoints.extraPoints
        if (!moodJournal.reflection.isNullOrBlank()) reward += rewardPoints.extraPoints

        return reward
    }

    // Outros métodos: calculateForMealJournal, etc...
}
