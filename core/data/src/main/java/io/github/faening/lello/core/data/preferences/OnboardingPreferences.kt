package io.github.faening.lello.core.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object OnboardingPreferencesKeys {
    val HAS_SEEN_ONBOARDING = booleanPreferencesKey("has_seen_onboarding")
}

class OnboardingPreferences(private val dataStore: DataStore<Preferences>) {
    val hasSeenOnboarding: Flow<Boolean> = dataStore.data.map { it[OnboardingPreferencesKeys.HAS_SEEN_ONBOARDING] ?: false }

    suspend fun setHasSeenOnboarding() {
        dataStore.edit { it[OnboardingPreferencesKeys.HAS_SEEN_ONBOARDING] = true }
    }
}