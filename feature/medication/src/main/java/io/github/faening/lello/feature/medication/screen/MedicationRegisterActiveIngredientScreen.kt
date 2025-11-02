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
import io.github.faening.lello.core.designsystem.component.textfield.LelloSimpleSearchTextField
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationRegisterActiveIngredientScreen(
    viewModel: MedicationViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    val activeIngredients by viewModel.activeIngredients.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedActiveIngredient by viewModel.selectedActiveIngredient.collectAsState()

    MedicationRegisterActiveIngredientScreenContent(
        activeIngredients = activeIngredients,
        selectedActiveIngredient = selectedActiveIngredient,
        searchQuery = searchQuery,
        onSearchQueryChange = { viewModel.updateSearchQuery(it) },
        onSelectActiveIngredient = { viewModel.selectActiveIngredient(it) },
        onBack = onBack,
        onNext = onNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationRegisterActiveIngredientScreenContent(
    moodColor: MoodColor = MoodColor.DEFAULT,
    activeIngredients: List<MedicationActiveIngredientOption> = emptyList(),
    selectedActiveIngredient: MedicationActiveIngredientOption? = null,
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onSelectActiveIngredient: (MedicationActiveIngredientOption) -> Unit = {},
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    Scaffold(
        topBar = {
            LelloTopAppBar(
                title = TopAppBarTitle(text = "Registrar remédio"),
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
                    enabled = selectedActiveIngredient != null,
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
                text = "Qual é o remédio que você quer cadastrar?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingRegular)
            )
            Text(
                text = "Pesquise pelo princípio ativo do remédio. Informe pelo menos 3 caracteres para iniciar a " +
                        "busca.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )
            LelloSimpleSearchTextField(
                value = searchQuery,
                onValueChange = { onSearchQueryChange(it) },
                placeholder = "EX: VENLAFAXINA",
                toUpperCase = true
            )
            Column(modifier = Modifier.padding(top = Dimension.spacingExtraLarge)) {
                LelloMedicationActiveIngredientOptionList(
                    items = activeIngredients,
                    onSelect = { option -> onSelectActiveIngredient(option as MedicationActiveIngredientOption) },
                    searchQuery = searchQuery
                )
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
private fun MedicationRegisterActiveIngredientScreenPreview_LightMode() {
    LelloTheme {
        MedicationRegisterActiveIngredientScreenContent(
            moodColor = MoodColor.DEFAULT,
            onBack = {},
            onNext = {}
        )
    }
}

// endregion Previews