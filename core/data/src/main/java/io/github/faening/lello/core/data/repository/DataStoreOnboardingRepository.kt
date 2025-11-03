package io.github.faening.lello.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import io.github.faening.lello.core.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreOnboardingRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingRepository {

    override val hasSeenOnboarding: Flow<Boolean> = dataStore.data.map { it[HAS_SEEN_ONBOARDING] ?: false }

    override suspend fun setHasSeenOnboarding() {
        dataStore.edit { it[HAS_SEEN_ONBOARDING] = true }
    }

    companion object {
        val HAS_SEEN_ONBOARDING = booleanPreferencesKey("has_seen_onboarding")
    }
}