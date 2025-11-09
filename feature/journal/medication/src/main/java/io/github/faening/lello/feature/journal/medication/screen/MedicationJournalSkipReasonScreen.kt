package io.github.faening.lello.feature.journal.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.pill.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import io.github.faening.lello.core.testing.data.MedicationTestData
import io.github.faening.lello.feature.journal.medication.MedicationJournalViewModel

@Composable
fun MedicationJournalSkipReasonScreen(
    viewModel: MedicationJournalViewModel,
    onBack: () -> Unit,
    onOpenSettings: () -> Unit,
    onFinish: () -> Unit
) {
    val selectedMedication by viewModel.selectedMedication.collectAsState()
    val selectedDosageIndex by viewModel.selectedDosageIndex.collectAsState()
    val skipReasonOptions by viewModel.skipReasonOptions.collectAsState()

    MedicationJournalSkipReasonContent(
        medication = selectedMedication,
        dosageIndex = selectedDosageIndex,
        skipReasonOptions =skipReasonOptions,
        onBack = onBack,
        onOpenSettings = onOpenSettings,
        onSaveAndFinish = { taken, skipReason ->
            viewModel.saveMedicationJournal(taken, skipReason)
            onFinish()
        }
    )
}

@Composable
private fun MedicationJournalSkipReasonContent(
    medication: Medication?,
    dosageIndex: Int?,
    skipReasonOptions: List<MedicationSkipReasonOption>,
    onBack: () -> Unit,
    onOpenSettings: () -> Unit,
    onSaveAndFinish: (Boolean, MedicationSkipReasonOption?) -> Unit
) {
    var showSkipReasons by remember { mutableStateOf(false) }
    var selectedSkipReason by remember { mutableStateOf<MedicationSkipReasonOption?>(null) }

    Scaffold(
        topBar = {
            MedicationJournalSkipReasonTopAppBar(onBack = onBack)
        },
        bottomBar = {
            if (showSkipReasons) {
                MedicationJournalSkipReasonBottomBar(
                    onFinish = { onSaveAndFinish(false, selectedSkipReason) },
                    isEnabled = selectedSkipReason != null
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            MedicationJournalSkipReasonHeaderSection(
                dosageNumber = (dosageIndex ?: 0) + 1,
                medicationName = medication?.activeIngredientOption?.description ?: ""
            )

            MedicationJournalSkipReasonActionSection(
                skipReasonOptions = skipReasonOptions,
                onTaken = { onSaveAndFinish(true, null) },
                onShowSkipReasons = { showSkipReasons = true },
                selectedSkipReason = selectedSkipReason,
                onSkipReasonSelected = { selectedSkipReason = it },
                onOpenSettings = onOpenSettings,
                showSkipReasons = showSkipReasons
            )
        }
    }
}

@Composable
private fun MedicationJournalSkipReasonTopAppBar(
    onBack: () -> Unit,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Remédios"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun MedicationJournalSkipReasonBottomBar(
    onFinish: () -> Unit,
    isEnabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Concluir",
            onClick = onFinish,
            enabled = isEnabled
        )
    }
}

@Composable
private fun MedicationJournalSkipReasonHeaderSection(
    dosageNumber: Int,
    medicationName: String
) {
    Text(
        text = "Você já tomou a ${dosageNumber}ª dose do remédio?",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = Dimension.spacingRegular)
    )
    Text(
        text = medicationName.uppercase(),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
    )
}

@Composable
private fun MedicationJournalSkipReasonActionSection(
    skipReasonOptions: List<MedicationSkipReasonOption>,
    onTaken: () -> Unit,
    onShowSkipReasons: () -> Unit,
    selectedSkipReason: MedicationSkipReasonOption?,
    onSkipReasonSelected: (MedicationSkipReasonOption) -> Unit,
    onOpenSettings: () -> Unit,
    showSkipReasons: Boolean
) {
    // var showSkipReasons by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimension.spacingLarge),
            horizontalArrangement = Arrangement.spacedBy(Dimension.spacingRegular)
        ) {
            LelloFilledButton(
                label = "Sim",
                onClick = onTaken,
                enabled = !showSkipReasons,
                modifier = Modifier.weight(1f)
            )
            LelloFilledButton(
                label = "Não",
                onClick = onShowSkipReasons,
                modifier = Modifier.weight(1f)
            )
        }

        if (showSkipReasons) {
            LelloOptionPillSelector(
                title = "Por que você não tomou o remédio?",
                options = skipReasonOptions,
                isSelected = { it == selectedSkipReason },
                onToggle = onSkipReasonSelected,
                onOpenSettings = onOpenSettings,
                getLabel = { it.description }
            )
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
private fun MedicationJournalSkipReasonScreenPreview_Default_LightMode() {
    val medication = MedicationTestData.list.first()

    LelloTheme {
        MedicationJournalSkipReasonContent(
            medication = medication,
            dosageIndex = 1,
            skipReasonOptions = emptyList(),
            onBack = {},
            onSaveAndFinish = { _, _ -> },
            onOpenSettings = {}
        )
    }
}

// endregion Previews