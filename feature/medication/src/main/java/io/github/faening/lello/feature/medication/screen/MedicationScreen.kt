package io.github.faening.lello.feature.medication.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationScreen(
    viewModel: MedicationViewModel
) {
    LelloTheme {
        MedicationContainer()
    }
}

@Composable
private fun MedicationContainer() {
    Scaffold(
        topBar = { MedicationTopAppBar() },
    ) { paddingValues ->
        MedicationContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun MedicationTopAppBar() {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Medicamentos")
    )
}


@Composable
private fun MedicationContent(
    modifier: Modifier = Modifier
) {

}