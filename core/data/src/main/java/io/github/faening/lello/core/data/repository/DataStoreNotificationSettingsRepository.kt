package io.github.faening.lello.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import io.github.faening.lello.core.domain.repository.NotificationSettingsResources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreNotificationSettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : NotificationSettingsResources {

    override suspend fun getJournalRewardsNotificationEnabled(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[JOURNAL_REWARDS_NOTIFICATION_KEY] ?: true
        }.first()
    }

    override suspend fun setJournalRewardsNotificationEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[JOURNAL_REWARDS_NOTIFICATION_KEY] = enabled
        }
    }

    override fun observeJournalRewardsNotificationEnabled(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[JOURNAL_REWARDS_NOTIFICATION_KEY] ?: true
        }
    }

    override suspend fun getMedicationNotificationEnabled(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[MEDICATION_NOTIFICATION_KEY] ?: true
        }.first()
    }

    override suspend fun setMedicationNotificationEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[MEDICATION_NOTIFICATION_KEY] = enabled
        }
    }

    override fun observeMedicationNotificationEnabled(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[MEDICATION_NOTIFICATION_KEY] ?: true
        }
    }

    override suspend fun getMascotEnergyNotificationEnabled(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[MASCOT_ENERGY_NOTIFICATION_KEY] ?: true
        }.first()
    }

    override suspend fun setMascotEnergyNotificationEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[MASCOT_ENERGY_NOTIFICATION_KEY] = enabled
        }
    }

    override fun observeMascotEnergyNotificationEnabled(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[MASCOT_ENERGY_NOTIFICATION_KEY] ?: true
        }
    }

    companion object {
        private val JOURNAL_REWARDS_NOTIFICATION_KEY = booleanPreferencesKey("journal_rewards_notification")
        private val MEDICATION_NOTIFICATION_KEY = booleanPreferencesKey("medication_notification")
        private val MASCOT_ENERGY_NOTIFICATION_KEY = booleanPreferencesKey("mascot_energy_notification")
    }
}