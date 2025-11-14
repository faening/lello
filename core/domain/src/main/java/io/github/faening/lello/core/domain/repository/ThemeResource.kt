package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ThemeResource {
    val isDarkThemeEnabled: Flow<Boolean>
    suspend fun setDarkThemeEnabled(enabled: Boolean)
}