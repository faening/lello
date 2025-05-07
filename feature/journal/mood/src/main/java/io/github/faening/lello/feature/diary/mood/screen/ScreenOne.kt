package io.github.faening.lello.feature.diary.mood.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenOne(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigateToScreenTwo: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Screen One")

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Voltar")
        }

        Button(
            onClick = onNavigateToScreenTwo,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Ir para ScreenTwo")
        }
    }
}