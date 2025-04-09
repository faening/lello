package io.github.faening.lello.core.model.diary

import androidx.compose.ui.graphics.vector.ImageVector

data class DiarySettingsOption(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val isEnabled: Boolean
)