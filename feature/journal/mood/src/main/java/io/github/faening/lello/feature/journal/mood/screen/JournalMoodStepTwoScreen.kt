package io.github.faening.lello.feature.journal.mood.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun JournalMoodStepTwoScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Screen Two")

        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Voltar")
        }
    }
}