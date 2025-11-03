package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.list.LelloMedicationActiveIngredientOptionList
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.testing.data.MedicationDosageFormOptionTestData
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationRegisterFormScreen(
    viewModel: MedicationViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    val dosageForms by viewModel.dosageFormOptions.collectAsState()
    val selectedActiveIngredient by viewModel.selectedActiveIngredient.collectAsState()
    val selectedDosageForm by viewModel.selectedDosageForm.collectAsState()

    MedicationRegisterFormScreenContent(
        selectedActiveIngredient = selectedActiveIngredient,
        dosageForms = dosageForms,
        onSelectDosageForm = { viewModel.updateDosageForm(it) },
        selectedDosageForm = selectedDosageForm,
        onBack = onBack,
        onNext = onNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationRegisterFormScreenContent(
    moodColor: MoodColor = MoodColor.DEFAULT,
    selectedActiveIngredient: MedicationActiveIngredientOption? = null,
    dosageForms: List<MedicationDosageFormOption>,
    onSelectDosageForm: (MedicationDosageFormOption) -> Unit = {},
    selectedDosageForm: MedicationDosageFormOption? = null,
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    Scaffold(
        topBar = {
            LelloTopAppBar(
                title = TopAppBarTitle(text = "Cadastrar remédio"),
                navigateUp = TopAppBarAction(onClick = onBack),
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
                LelloFloatingActionButton(
                    icon = LelloIcons.Outlined.ArrowRightLarge.imageVector,
                    contentDescription = "Próximo",
                    enabled = selectedDosageForm != null,
                    moodColor = moodColor,
                    onClick = onNext
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
            Text(
                text = "Qual é a apresentação deste remédio?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingRegular)
            )
            Text(
                text = "${selectedActiveIngredient?.description?.uppercase()}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )
            LelloMedicationActiveIngredientOptionList(
                items = dosageForms,
                onSelect = { option -> onSelectDosageForm(option as MedicationDosageFormOption) }
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
private fun MedicationRegisterFormScreenPreview_LightMode() {
    val items = MedicationDosageFormOptionTestData.list
    
    LelloTheme {
        MedicationRegisterFormScreenContent(
            moodColor = MoodColor.DEFAULT,
            dosageForms = items,
            onBack = {},
            onNext = {}
        )
    }
}

// endregion Previews