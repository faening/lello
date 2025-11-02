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

    MedicationScreenContent(
        medications = medications,
        onRegister = onRegister
    )
}

@Composable
private fun MedicationScreenContent(
    medications: List<Medication>,
    onRegister: () -> Unit,
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
            MedicationHeaderSection()

            if (medications.isEmpty()) {
                MedicationEmptyContentSection()
            } else {
                MedicationContentListSection(medications)
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
            label = "Cadastrar Remédio",
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
    medications: List<Medication>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingMedium)
    ) {
        items(
            count = medications.size,
            key = { index -> medications[index].id ?: index }
        ) { index ->
            LelloMedicationCard(
                medication = medications[index],
                onDosageClick = { dosageIndex ->
                    // TODO: Navegar para tela de edição com o índice da dosagem
                }
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
private fun MedicationScreenPreview_EmptyState_LightMode() {
    LelloTheme {
        MedicationScreenContent(
            medications = emptyList(),
            onRegister = {}
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
            onRegister = {}
        )
    }
}

// endregion Previews