package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationScreen(
    viewModel: MedicationViewModel,
    onRegisterMedication: () -> Unit,
) {

    MedicationScreenContent(
        onRegisterMedication = onRegisterMedication
    )
}

@Composable
private fun MedicationScreenContent(
    moodColor : MoodColor = MoodColor.DEFAULT,
    medications: List<Medication> = emptyList(),
    onRegisterMedication: () -> Unit,
) {
    Scaffold(
        topBar = {
            LelloTopAppBar(
                title = TopAppBarTitle(text = "Remédios"),
                moodColor = MoodColor.INVERSE
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimension.spacingRegular),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LelloFilledButton(
                    label = "Cadastrar Remédio",
                    onClick = { onRegisterMedication() },
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (medications.isEmpty()) {
                Text(
                    text = "Você ainda não registrou nenhum remédio.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = Dimension.spacingRegular)
                )
            }

            // Medication list
        }
    }
}

// region Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun SettingsScreenPreview_LightMode_EmptyState() {
    LelloTheme {
        MedicationScreenContent(
            moodColor = MoodColor.DEFAULT,
            medications = emptyList(),
            onRegisterMedication = {}
        )
    }
}

// endregion Previews