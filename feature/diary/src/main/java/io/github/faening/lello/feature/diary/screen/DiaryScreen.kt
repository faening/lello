package io.github.faening.lello.feature.diary.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.github.faening.lello.core.designsystem.component.appbar.LelloCalendarTopAppBar
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.diary.DiaryViewModel
import java.time.LocalDate

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel
) {
    val selectedDate by viewModel.selectedDate.collectAsState()

    LelloTheme {
        DiaryContainer(
            selectedDate = selectedDate,
            onSelectDate = viewModel::setSelectedDate
        )
    }
}

@Composable
private fun DiaryContainer(
    selectedDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit = {}
) {
    Scaffold(
        topBar = {
            DiaryTopAppBar(
                selectedDate = selectedDate,
                onDateSelected = onSelectDate
            )
        },
    ) { paddingValues ->
        DiaryContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun DiaryTopAppBar(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    LelloCalendarTopAppBar(
        selectedDate = selectedDate,
        onDateSelected = onDateSelected
    )
}

@Composable
private fun DiaryContent(
    modifier: Modifier = Modifier
) {

}