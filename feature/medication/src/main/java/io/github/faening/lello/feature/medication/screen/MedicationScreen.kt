package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.card.LelloMedicationCard
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.testing.data.MedicationTestData
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationScreen(
    viewModel: MedicationViewModel,
    onRegister: () -> Unit,
) {
    val medications by viewModel.medications.collectAsState()
    var medicationToDisable by remember { mutableStateOf<Medication?>(null) }

    MedicationScreenContent(
        medications = medications,
        onRegister = onRegister,
        onDisableRequest = { medication ->
            medicationToDisable = medication
        }
    )

    medicationToDisable?.let { medication ->
        DisableMedicationDialog(
            medication = medication,
            onConfirm = {
                viewModel.disableMedication(medication)
                medicationToDisable = null
            },
            onDismiss = {
                medicationToDisable = null
            }
        )
    }
}

@Composable
private fun MedicationScreenContent(
    medications: List<Medication>,
    onRegister: () -> Unit,
    onDisableRequest: (Medication) -> Unit
) {
    Scaffold(
        topBar = {
            MedicationTopAppBar()
        },
        bottomBar = {
            MedicationBottomBar(onRegister)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            // MedicationHeaderSection()

            if (medications.isEmpty()) {
                MedicationEmptyContentSection()
            } else {
                MedicationContentListSection(
                    medications = medications,
                    onDisableRequest = onDisableRequest
                )
            }
        }
    }
}

@Composable
private fun MedicationTopAppBar() {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Remédios"),
        moodColor = MoodColor.INVERSE
    )
}

@Composable
private fun MedicationBottomBar(
    onRegister: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimension.spacingRegular,
                end = Dimension.spacingRegular,
                bottom = Dimension.spacingRegular
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Cadastrar remédio",
            onClick = { onRegister() },
        )
    }
}

@Composable
private fun MedicationHeaderSection() {
    Text(
        text = "Abaixo, está a lista dos seus remédios cadastrados",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
    )
}

@Composable
private fun MedicationEmptyContentSection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Você ainda não registrou nenhum remédio.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun MedicationContentListSection(
    medications: List<Medication>,
    onDisableRequest: (Medication) -> Unit
) {
    val activeMedications = medications.filter { it.active == true }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingMedium)
    ) {
        items(
            count = activeMedications.size,
            key = { index -> activeMedications[index].id ?: index }
        ) { index ->
            LelloMedicationCard(
                medication = activeMedications[index],
                onDosageClick = { dosageIndex ->
                    // TODO: Navegar para tela de edição com o índice da dosagem
                },
                onDisable = {
                    onDisableRequest(activeMedications[index])
                }
            )
        }
    }
}

@Composable
private fun DisableMedicationDialog(
    medication: Medication,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Desativar remédio")
        },
        text = {
            Text("Tem certeza que deseja desativar o remédio ${medication.activeIngredientOption?.description}?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Sim")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Não")
            }
        }
    )
}

// region Previews

@Composable
@Preview(
    name = "Empty State",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun MedicationScreenPreview_EmptyState_LightMode() {
    LelloTheme {
        MedicationScreenContent(
            medications = emptyList(),
            onRegister = {},
            onDisableRequest = {}
        )
    }
}

@Composable
@Preview(
    name = "Empty State",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun MedicationScreenPreview_MedicationList_LightMode() {
    val medications = MedicationTestData.list

    LelloTheme {
        MedicationScreenContent(
            medications = medications,
            onRegister = {},
            onDisableRequest = {}
        )
    }
}

// endregion Previews