package io.github.faening.lello.core.domain.usecase.reward

import io.github.faening.lello.core.domain.repository.DailyCheckInRepository
import io.github.faening.lello.core.domain.usecase.journal.meal.GetAllMealJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetAllMedicationJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.mood.GetAllMoodJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.GetAllSleepJournalsUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.ObserveRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.domain.util.isSameDay
import io.github.faening.lello.core.model.reward.DailyCheckInData
import io.github.faening.lello.core.model.reward.DailyCheckInState
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import io.github.faening.lello.core.model.reward.RewardPoints
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import java.time.LocalDate
import javax.inject.Inject

class GetDailyCheckInUseCase @Inject constructor(
    private val getAllMealJournalsUseCase: GetAllMealJournalsUseCase,
    private val getAllMedicationJournalsUseCase: GetAllMedicationJournalsUseCase,
    private val getAllMoodJournalsUseCase: GetAllMoodJournalsUseCase,
    private val getAllSleepJournalsUseCase: GetAllSleepJournalsUseCase,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val observeRewardBalanceUseCase: ObserveRewardBalanceUseCase,
    private val saveRewardHistoryUseCase: SaveRewardHistoryUseCase
) : DailyCheckInRepository {

    override fun observeDailyCheckIn(): Flow<DailyCheckInState> {
        return combine(
            getAllMealJournalsUseCase.invoke(),
            getAllMedicationJournalsUseCase.invoke(),
            getAllMoodJournalsUseCase.invoke(),
            getAllSleepJournalsUseCase.invoke(),
            observeRewardBalanceUseCase.invoke()
        ) { meal, medication, mood, sleep, balance ->
            DailyCheckInData(meal, medication, mood, sleep, balance)
        }.mapLatest { data ->
            calculateState(data)
        }
    }

    private suspend fun calculateState(data: DailyCheckInData): DailyCheckInState {
        val today = LocalDate.now()
        var steps = 0

        if (data.mealJournals.any { it.createdAt.isSameDay(today) }) steps++
        if (data.medicationJournals.any { it.createdAt.isSameDay(today) }) steps++
        if (data.moodJournals.any { it.createdAt.isSameDay(today) }) steps++
        if (data.sleepJournals.any { it.createdAt.isSameDay(today) }) steps++

        val bonusAlreadyGiven = data.rewardBalance.lastDailyAchievementReward?.isSameDay(today) == true

        if (steps >= 4 && !bonusAlreadyGiven) {
            val now = System.currentTimeMillis()
            val updatedBalance = data.rewardBalance.copy(
                totalCoins = data.rewardBalance.totalCoins + RewardPoints.DAILY_ACHIEVEMENTS.basePoints,
                lastDailyAchievementReward = now
            )
            saveOrUpdateRewardBalanceUseCase.invoke(updatedBalance)
            val history = RewardHistory(
                rewardOrigin = RewardOrigin.DAILY_ACHIEVEMENT,
                rewardAmount = RewardPoints.DAILY_ACHIEVEMENTS.basePoints,
                createdAt = now
            )
            saveRewardHistoryUseCase.invoke(history)
            return DailyCheckInState(currentStep = steps, bonusReceived = true)
        }

        return DailyCheckInState(currentStep = steps, bonusReceived = bonusAlreadyGiven)
    }
}