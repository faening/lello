package io.github.faening.lello.core.model.reward

import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal

data class DailyCheckInData(
    val moodJournals: List<MoodJournal>,
    val mealJournals: List<MealJournal>,
    val sleepJournals: List<SleepJournal>,
    val rewardBalance: RewardBalance
)