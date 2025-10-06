package io.github.faening.lello.core.data.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.github.faening.lello.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreUserRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserRepository {

    override suspend fun saveUserEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }

    override suspend fun getUserEmail(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_EMAIL]
    }

    override suspend fun setBiometricPreference(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[BIOMETRIC_ENABLED] = enabled
        }
    }

    override suspend fun getBiometricPreference(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[BIOMETRIC_ENABLED] ?: false
    }

    private companion object {
        val USER_EMAIL = stringPreferencesKey("user_email")
        val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
    }
}