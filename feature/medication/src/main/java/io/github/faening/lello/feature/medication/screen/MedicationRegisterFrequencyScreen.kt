package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationRegisterFrequencyScreen(
    viewModel: MedicationViewModel,
    onBack: () -> Unit,
    onRegister: () -> Unit,
) {
    val selectedActiveIngredient by viewModel.selectedActiveIngredient.collectAsState()

    MedicationRegisterFrequencyScreenContent(
        selectedActiveIngredient = selectedActiveIngredient,
        onBack = onBack,
        onRegister = onRegister
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationRegisterFrequencyScreenContent(
    moodColor: MoodColor = MoodColor.DEFAULT,
    selectedActiveIngredient: MedicationActiveIngredientOption? = null,
    dosages: List<Any> = emptyList(),
    onBack: () -> Unit,
    onRegister: () -> Unit,
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
                LelloFilledButton(
                    label = "Cadastrar",
                    onClick = onRegister
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            // Header
            Text(
                text = "Cadastre as dosagens para o remédio selecionado:",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingRegular)
            )
            Text(
                text = "${selectedActiveIngredient?.description?.uppercase()}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )

            if (dosages.isEmpty()) {
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
            } else {
                // Dosage list would go here
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
private fun MedicationRegisterFrequencyScreenPreview_LightMode() {
    LelloTheme {
        MedicationRegisterFrequencyScreenContent(
            moodColor = MoodColor.DEFAULT,
            onBack = {},
            onRegister = {}
        )
    }
}

// endregion Previews