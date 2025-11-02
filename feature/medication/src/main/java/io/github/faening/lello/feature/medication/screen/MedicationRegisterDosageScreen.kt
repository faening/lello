package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.button.LelloFlowItemButton
import io.github.faening.lello.core.designsystem.component.pill.LelloFilledPill
import io.github.faening.lello.core.designsystem.component.textfield.LelloDosageTextField
import io.github.faening.lello.core.designsystem.component.textfield.LelloSimpleSearchTextField
import io.github.faening.lello.core.designsystem.component.textfield.SimpleSearchTextFieldDefaults
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationRegisterDosageScreen(
    viewModel: MedicationViewModel,
    onBack: () -> Unit
) {
    val selectedActiveIngredient by viewModel.selectedActiveIngredient.collectAsState()
    val dosageQuantity by viewModel.dosageQuantityString.collectAsState()
    val dosageUnitOptions by viewModel.dosageUnitOptions.collectAsState()
    val selectedDosageUnit by viewModel.selectedDosageUnit.collectAsState()
    val selectedDosageTime by viewModel.selectedDosageTime.collectAsState()
    val isDosageValid by viewModel.isDosageValid.collectAsState()

    MedicationRegisterDosageScreenContent(
        selectedActiveIngredient = selectedActiveIngredient,
        dosageQuantity = dosageQuantity,
        onDosageQuantityChange = viewModel::updateDosageQuantity,
        dosageUnitOptions = dosageUnitOptions,
        selectedDosageUnit = selectedDosageUnit,
        onDosageUnitSelect = viewModel::selectDosageUnit,
        selectedDosageTime = selectedDosageTime,
        onDosageTimeChange = viewModel::updateSelectedDosageTime,
        isDosageValid = isDosageValid,
        onBack = onBack,
        onSave = {
            viewModel.saveDosage()
            onBack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationRegisterDosageScreenContent(
    selectedActiveIngredient: MedicationActiveIngredientOption? = null,
    dosageQuantity: String,
    onDosageQuantityChange: (String) -> Unit = {},
    dosageUnitOptions: List<MedicationDosageUnitOption> = emptyList(),
    selectedDosageUnit: MedicationDosageUnitOption? = null,
    onDosageUnitSelect: (MedicationDosageUnitOption?) -> Unit = {},
    selectedDosageTime: String,
    onDosageTimeChange: (String) -> Unit = {},
    isDosageValid: Boolean = false,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            MedicationRegisterDosageTopAppBar(onBack)
        },
        bottomBar = {
            MedicationRegisterDosageBottomBar(isDosageValid, onSave)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            MedicationRegisterDosageHeaderSection(selectedActiveIngredient)

            MedicationRegisterDosageUnitSection(
                dosageQuantity,
                onDosageQuantityChange,
                selectedDosageUnit,
                onDosageUnitSelect,
                dosageUnitOptions
            )

            MedicationRegisterDosageTimeSection(
                selectedDosageTime,
                onDosageTimeChange
            )
        }
    }
}

@Composable
private fun MedicationRegisterDosageTopAppBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Registrar remédio"),
        navigateUp = TopAppBarAction(onClick = onBack),
        moodColor = MoodColor.INVERSE
    )
}

@Composable
private fun MedicationRegisterDosageBottomBar(
    isEnabled: Boolean,
    onSave: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Salvar",
            enabled = isEnabled,
            onClick = onSave
        )
    }
}

@Composable
private fun MedicationRegisterDosageHeaderSection(
    selectedActiveIngredient: MedicationActiveIngredientOption?
) {
    Text(
        text = "Cadastre a dosagem para o remédio selecionado",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
    )
    Text(
        text = "${selectedActiveIngredient?.description?.uppercase()}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MedicationRegisterDosageUnitSection(
    dosageQuantity: String,
    onDosageQuantityChange: (String) -> Unit,
    selectedDosageUnit: MedicationDosageUnitOption?,
    onDosageUnitSelect: (MedicationDosageUnitOption?) -> Unit,
    dosageUnitOptions: List<MedicationDosageUnitOption>
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.spacingExtraLarge)
    ) {
        Text(
            text = "Qual é a quantidade total dessa dose?",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = Dimension.spacingRegular)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimension.spacingRegular),
            horizontalArrangement = Arrangement.spacedBy(Dimension.spacingRegular),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Dosage Quantity
            LelloDosageTextField(
                value = dosageQuantity,
                onValueChange = { onDosageQuantityChange(it) },
                placeholder = "Ex: 1 ou 0.5",
                modifier = Modifier
                    .padding(end = Dimension.spacingSmall)
                    .weight(1f)
            )

            // Dosage Unit
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.weight(2f)
            ) {
                LelloSimpleSearchTextField(
                    value = selectedDosageUnit?.description ?: "",
                    onValueChange = { },
                    placeholder = "Unidade",
                    trailingIcon = {
                        if (selectedDosageUnit?.description?.isNotEmpty() == true) {
                            IconButton(
                                onClick = { onDosageUnitSelect(null) },
                                modifier = Modifier.size(Dimension.iconSizeDefault)
                            ) {
                                Icon(
                                    imageVector = LelloIcons.Outlined.Close.imageVector,
                                    contentDescription = "Open Menu",
                                    tint = SimpleSearchTextFieldDefaults.drawColor()
                                )
                            }
                        }
                    },
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    dosageUnitOptions.forEach { unit ->
                        DropdownMenuItem(
                            text = { Text(unit.description) },
                            onClick = {
                                onDosageUnitSelect(unit)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MedicationRegisterDosageTimeSection(
    selectedDosageTime: String,
    onDosageTimeChange: (String) -> Unit
) {
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Label
        Text(
            text = "Qual horário você costuma tomar?",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = Dimension.spacingRegular)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Selected Time Pill
            LelloFilledPill(
                label = selectedDosageTime,
                onClick = {},
                modifier = Modifier.padding(end = Dimension.spacingSmall)
            )

            // Edit Button
            LelloFlowItemButton(
                icon = LelloIcons.Outlined.Edit.imageVector,
                contentDescription = "Atualizar",
                onClick = { showTimePicker = true },
                modifier = Modifier
            )
        }
    }

    // Time Picker Dialog
    if (showTimePicker) {
        BasicAlertDialog(
            onDismissRequest = { showTimePicker = false },
            modifier = Modifier
                .clip(RoundedCornerShape(Dimension.borderRadiusLarge))
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(Dimension.spacingLarge),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(
                    state = timePickerState,
                    layoutType = TimePickerLayoutType.Vertical,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimension.spacingSmall),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showTimePicker = false }) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = {
                            val hour = timePickerState.hour.toString().padStart(2, '0')
                            val minute = timePickerState.minute.toString().padStart(2, '0')
                            onDosageTimeChange("$hour:$minute")
                            showTimePicker = false
                        }
                    ) { Text("OK") }
                }
            }
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
private fun MedicationRegisterDosageScreenPreview_LightMode() {
    LelloTheme {
        MedicationRegisterDosageScreenContent(
            selectedActiveIngredient = null,
            dosageQuantity = "",
            onDosageQuantityChange = {},
            dosageUnitOptions = emptyList(),
            selectedDosageUnit = null,
            onDosageUnitSelect = {},
            selectedDosageTime = "22:00",
            onDosageTimeChange = {},
            onBack = {},
            onSave = {}
        )
    }
}

// endregion Previews