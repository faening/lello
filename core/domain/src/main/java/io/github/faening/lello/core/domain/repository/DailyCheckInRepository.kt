package io.github.faening.lello.core.domain.repository

import io.github.faening.lello.core.model.reward.DailyCheckInState
import kotlinx.coroutines.flow.Flow

interface DailyCheckInRepository {
    fun observeDailyCheckIn(): Flow<DailyCheckInState>
}