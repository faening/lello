package io.github.faening.lello.feature.menu.achievement.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen(
    onBackClick: () -> Unit
) {
    Column {
        Text("Achievements")
    }
}