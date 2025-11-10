package io.github.faening.lello.feature.diary.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.model.journal.MedicationJournal
import io.github.faening.lello.feature.diary.DiaryViewModel

@Composable
fun DiaryMedicationDetailsScreen(
    viewModel: DiaryViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val selectedMedicationJournal by viewModel.selectedMedicationJournal.collectAsState()

    DiaryMedicationDetailsScreenContent(
        medicationJournal = selectedMedicationJournal ?: return,
        onBackClick = onBackClick
    )
}

@Composable
private fun DiaryMedicationDetailsScreenContent(
    medicationJournal: MedicationJournal,
    onBackClick: () -> Unit,
) {

}