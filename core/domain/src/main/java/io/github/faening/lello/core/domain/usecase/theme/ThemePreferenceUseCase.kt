package io.github.faening.lello.core.domain.usecase.theme

import io.github.faening.lello.core.domain.repository.ThemeResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemePreferenceUseCase @Inject constructor(
    private val themeResource: ThemeResource
) {
    val isDarkThemeEnabled: Flow<Boolean> = themeResource.isDarkThemeEnabled

    suspend fun setDarkTheme(enabled: Boolean) {
        themeResource.setDarkThemeEnabled(enabled)
    }
}