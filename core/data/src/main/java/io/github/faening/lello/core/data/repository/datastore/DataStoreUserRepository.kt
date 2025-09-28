package io.github.faening.lello.core.data.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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

    private companion object {
        val USER_EMAIL = stringPreferencesKey("user_email")
    }
}