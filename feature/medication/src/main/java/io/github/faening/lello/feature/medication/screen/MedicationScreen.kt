package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.list.LelloMedicationActiveIngredientOptionList
import io.github.faening.lello.core.designsystem.component.textfield.LelloSimpleSearchTextField
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationScreen(
    viewModel: MedicationViewModel,
    onNext: () -> Unit,
) {
    val activeIngredients by viewModel.activeIngredients.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    MedicationScreenContent(
        activeIngredients = activeIngredients,
        searchQuery = searchQuery,
        onSearchQueryChange = { viewModel.updateSearchQuery(it) },
        onSelectActiveIngredient = { viewModel.selectActiveIngredient(it) },
        onNext = onNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationScreenContent(
    moodColor: MoodColor = MoodColor.DEFAULT,
    activeIngredients: List<MedicationActiveIngredientOption> = emptyList(),
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onSelectActiveIngredient: (MedicationActiveIngredientOption) -> Unit = {},
    onNext: () -> Unit,
) {
    Scaffold(
        topBar = { MedicationTopAppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            // Header
            Text(
                text = "Qual é o medicamento que você quer cadastrar?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingRegular)
            )
            Text(
                text = "Pesquisa pelo nome ou princípio ativo. Informe pelo menos 3 caracteres para iniciar a busca.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )

            // Search Field
            LelloSimpleSearchTextField(
                value = searchQuery,
                onValueChange = { onSearchQueryChange(it) },
                placeholder = "EX: VENLAFAXINA",
                toUpperCase = true
            )

            // Active Ingredient List
            Column(
                modifier = Modifier.padding(top = Dimension.spacingExtraLarge)
            ) {
                LelloMedicationActiveIngredientOptionList(
                    items = activeIngredients,
                    onSelect = { option ->
                        onSelectActiveIngredient(option)
                        onNext()
                    },
                    searchQuery = searchQuery
                )
            }
        }
    }
}

@Composable
private fun MedicationTopAppBar(
    moodColor: MoodColor = MoodColor.INVERSE,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Remédios"),
        moodColor = moodColor
    )
}

// region Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun SettingsScreenPreview_LightMode() {
    LelloTheme {
        MedicationScreenContent(
            moodColor = MoodColor.DEFAULT,
            onNext = {}
        )
    }
}

// endregion Previews