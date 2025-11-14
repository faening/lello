package io.github.faening.lello.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import io.github.faening.lello.core.domain.repository.ThemeResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreThemeRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ThemeResource {

    private companion object {
        val DARK_THEME_KEY = booleanPreferencesKey("dark_theme_enabled")
    }

    override val isDarkThemeEnabled: Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }

    override suspend fun setDarkThemeEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = enabled
        }
    }
}