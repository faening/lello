package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.card.LelloMedicationDosageCard
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.testing.data.MedicationDosageTestData
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationRegisterFrequencyScreen(
    viewModel: MedicationViewModel,
    onBack: () -> Unit,
    onRegisterDosage: () -> Unit,
    onSaveSuccess: () -> Unit
) {
    val selectedActiveIngredient by viewModel.selectedActiveIngredient.collectAsState()
    val dosages by viewModel.stagedDosages.collectAsState()
    val isMedicationValid by viewModel.isMedicationValid.collectAsState()

    MedicationRegisterFrequencyScreenContent(
        selectedActiveIngredient = selectedActiveIngredient,
        dosages = dosages,
        isMedicationValid = isMedicationValid,
        onRemoveDosage = viewModel::removeDosage,
        onBack = onBack,
        onRegisterDosage = onRegisterDosage,
        onSave = {
            viewModel.saveMedication()
            onSaveSuccess()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationRegisterFrequencyScreenContent(
    selectedActiveIngredient: MedicationActiveIngredientOption? = null,
    dosages: List<MedicationDosage>,
    isMedicationValid: Boolean = false,
    onRemoveDosage: (MedicationDosage) -> Unit = {},
    onBack: () -> Unit,
    onRegisterDosage: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            MedicationRegisterFrequencyTopAppBar(onBack)
        },
        bottomBar = {
            MedicationRegisterFrequencyBottomBar(
                onRegisterDosage = onRegisterDosage,
                isMedicationValid = isMedicationValid,
                onSave = onSave
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            MedicationRegisterFrequencyHeaderSection(selectedActiveIngredient)

            if (dosages.isEmpty()) {
                MedicationRegisterFrequencyEmptyContentSection()
            } else {
                MedicationRegisterFrequencyDosageListSection(
                    dosages = dosages,
                    onRemoveDosage = onRemoveDosage
                )
            }
        }
    }
}

@Composable
private fun MedicationRegisterFrequencyTopAppBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Registrar remédio"),
        navigateUp = TopAppBarAction(onClick = onBack),
        moodColor = MoodColor.INVERSE
    )
}

@Composable
private fun MedicationRegisterFrequencyBottomBar(
    onRegisterDosage: () -> Unit,
    isMedicationValid: Boolean,
    onSave: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingMedium),
    ) {
        LelloFilledButton(
            label = "Cadastrar dose",
            onClick = onRegisterDosage,
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(bottom = Dimension.spacingMedium)
        )
        LelloFilledButton(
            label = "Salvar remédio",
            enabled = isMedicationValid,
            onClick = onSave
        )
    }
}

@Composable
private fun MedicationRegisterFrequencyHeaderSection(
    selectedActiveIngredient: MedicationActiveIngredientOption?
) {
    Text(
        text = "Dosagens cadastradas para o remédio selecionado",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = Dimension.spacingRegular)
    )
    Text(
        text = "${selectedActiveIngredient?.description?.uppercase()}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
    )
}

@Composable
private fun MedicationRegisterFrequencyEmptyContentSection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Você ainda não registrou nenhuma dose para este remédio.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = Dimension.spacingRegular)
        )
    }
}

@Composable
private fun MedicationRegisterFrequencyDosageListSection(
    dosages: List<MedicationDosage>,
    onRemoveDosage: (MedicationDosage) -> Unit
) {
    Text(
        text = "Dosagens cadastradas (${dosages.size}):",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(bottom = Dimension.spacingRegular)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingMedium)
    ) {
        items(dosages) { dosage ->
            LelloMedicationDosageCard(
                dosage = dosage,
                onRemove = { onRemoveDosage(dosage) }
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
private fun MedicationRegisterFrequencyScreenPreview_EmptyState_LightMode() {
    LelloTheme {
        MedicationRegisterFrequencyScreenContent(
            dosages = emptyList(),
            onBack = {},
            onRegisterDosage = {},
            onSave = {}
        )
    }
}

@Composable
@Preview(
    name = "Dosages List",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun MedicationRegisterFrequencyScreenPreview_DosagesList_LightMode() {
    val dosages = MedicationDosageTestData.list

    LelloTheme {
        MedicationRegisterFrequencyScreenContent(
            dosages = dosages,
            onBack = {},
            onRegisterDosage = {},
            onSave = {}
        )
    }
}

// endregion Previews