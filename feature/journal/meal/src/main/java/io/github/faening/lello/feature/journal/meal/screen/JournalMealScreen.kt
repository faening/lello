package io.github.faening.lello.feature.journal.meal.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalMealScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Tela de Diário de Alimentação")

        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Voltar")
        }
    }
}