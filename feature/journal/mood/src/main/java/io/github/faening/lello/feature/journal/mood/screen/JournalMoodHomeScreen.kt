package io.github.faening.lello.feature.journal.mood.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun JournalMoodHomeScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Tela de Diário de Humor")

        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Voltar")
        }

        Button(
            onClick = onNext,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Ir para ScreenOne")
        }
    }
}