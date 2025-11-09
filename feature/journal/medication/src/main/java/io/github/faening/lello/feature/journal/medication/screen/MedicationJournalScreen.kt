package io.github.faening.lello.feature.journal.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.card.LelloMedicationCard
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.testing.data.MedicationTestData
import io.github.faening.lello.feature.journal.medication.MedicationJournalViewModel

@Composable
fun MedicationJournalScreen(
    viewModel: MedicationJournalViewModel,
    onBack: () -> Unit,
    onRegister: () -> Unit,
    onNext: (Medication, Int) -> Unit,
) {
    val context = LocalContext.current
    val medications by viewModel.medications.collectAsState()
    val registeredDosageIds by viewModel.registeredDosageIds.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.prepareVideo(context)
    }

    MedicationJournalScreenContent(
        medications = medications,
        registeredDosageIds = registeredDosageIds,
        onBack = onBack,
        onRegister = onRegister,
        onNext = onNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationJournalScreenContent(
    medications: List<Medication>,
    registeredDosageIds: Set<Long>,
    onBack: () -> Unit,
    onRegister: () -> Unit,
    onNext: (Medication, Int) -> Unit
) {
    Scaffold(
        topBar = {
            MedicationJournalTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            if (medications.isEmpty()) {
                MedicationJournalEmptyContentSection(
                    onRegister = onRegister
                )
            } else {
                MedicationJournalContentListSection(
                    medications = medications,
                    registeredDosageIds = registeredDosageIds,
                    onNext = onNext
                )
            }
        }
    }
}

@Composable
private fun MedicationJournalTopAppBar(
    onBack: () -> Unit,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Remédios"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun MedicationJournalEmptyContentSection(
    onRegister: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Você ainda não registrou nenhum remédio.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
        )
        LelloFilledButton(
            label = "Cadastrar remédio",
            onClick = { onRegister() },
        )
    }
}

@Composable
private fun MedicationJournalContentListSection(
    medications: List<Medication>,
    registeredDosageIds: Set<Long>,
    onNext: (Medication, Int) -> Unit
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
            val medication = activeMedications[index]

            LelloMedicationCard(
                medication = medication,
                onDosageClick = { dosageIndex ->
                    val dosage = medication.dosages.getOrNull(dosageIndex)
                    if (!registeredDosageIds.contains(dosage?.dosageNumber?.toLong())) {
                        onNext(medication, dosageIndex)
                    }
                },
                registeredDosageIds = registeredDosageIds,
                isDisableEnabled = false,
                onDisable = { }
            )
        }
    }
}

// region Previews

@Composable
@Preview(
    name = "Empty State",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun MedicationJournalScreenPreview_EmptyState_LightMode() {
    LelloTheme {
        MedicationJournalScreenContent(
            medications = emptyList(),
            registeredDosageIds = emptySet(),
            onBack = {},
            onRegister = {},
            onNext = { _, _ -> }
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
private fun MedicationJournalScreenPreview_MedicationList_LightMode() {
    val medications = MedicationTestData.list

    LelloTheme {
        MedicationJournalScreenContent(
            medications = medications,
            registeredDosageIds = emptySet(),
            onBack = {},
            onRegister = {},
            onNext = { _, _ -> }
        )
    }
}

// endregion Previews