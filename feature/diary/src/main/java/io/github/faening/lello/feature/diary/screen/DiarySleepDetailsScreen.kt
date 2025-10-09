package io.github.faening.lello.feature.diary.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.pill.LelloFilledPill
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.SleepJournalMock
import io.github.faening.lello.core.domain.util.toLocalDateTime
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.feature.diary.DiaryViewModel
import java.time.format.DateTimeFormatter

@Composable
fun DiarySleepDetailsScreen(
    viewModel: DiaryViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val selectedSleepJournal by viewModel.selectedSleepJournal.collectAsState()

    DiarySleepDetailsScreenContent(
        sleepJournal = selectedSleepJournal ?: return,
        onBackClick = onBackClick
    )
}

@Composable
private fun DiarySleepDetailsScreenContent(
    sleepJournal: SleepJournal,
    onBackClick: () -> Unit,
) {
    LelloTheme {
        Scaffold(
            topBar = { TopAppBarSection(onBackClick) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimension.spacingMedium)
                    .verticalScroll(rememberScrollState())
            ) {
                sleepJournal.let { sleep ->
                    Column {
                        SleepTimestampSection(sleep)
                        SleepDurationSection(sleep)
                        SleeplessTimeSection(sleep)
                        SleepSensationOptionsSection(sleep)
                        SleepQualityOptionsSection(sleep)
                        SleepActivityOptionsSection(sleep)
                        LocationOptionsSection(sleep)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopAppBarSection(
    onBackClick: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Diário de Sono"),
        navigateUp = TopAppBarAction(onClick = onBackClick),
        moodColor = MoodColor.INVERSE
    )
}

@Composable
private fun SleepTimestampSection(
    sleep: SleepJournal
) {
    DetailSection(
        title = "Data e hora",
        content = {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH'h' mm'm'")
            Text(
                text = formatter.format(sleep.createdAt.toLocalDateTime()),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@Composable
private fun SleepDurationSection(
    sleep: SleepJournal
) {
    DetailSection(
        title = "Duração do sono",
        content = {
            LelloFilledPill(sleep.sleepDuration.description)
        }
    )
}

@Composable
private fun SleeplessTimeSection(
    sleep: SleepJournal
) {
    if (sleep.sleeplessTime > 0) {
        DetailSection(
            title = "Tempo acordado durante a noite",
            content = {
                Text(
                    text = "${sleep.sleeplessTime} minutos",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        )
    }
}

@Composable
private fun SleepSensationOptionsSection(
    sleep: SleepJournal
) {
    DetailSection(
        title = "Como você se sentiu ao acordar?",
        content = {
            if (sleep.sleepSensationOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    sleep.sleepSensationOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun SleepQualityOptionsSection(
    sleep: SleepJournal
) {
    DetailSection(
        title = "Como foi a qualidade do seu sono?",
        content = {
            if (sleep.sleepQualityOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    sleep.sleepQualityOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun SleepActivityOptionsSection(
    sleep: SleepJournal
) {
    DetailSection(
        title = "O que você fez antes de dormir?",
        content = {
            if (sleep.sleepActivityOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    sleep.sleepActivityOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun LocationOptionsSection(
    sleep: SleepJournal
) {
    DetailSection(
        title = "Onde você dormiu?",
        content = {
            if (sleep.locationOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    sleep.locationOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun DetailSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.spacingLarge),
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingMedium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        content()
    }
}

// region Previews

@Preview(
    name = "Default",
    group = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun DiarySleepDetailsScreenPreview_LightMode() {
    DiarySleepDetailsScreenContent(
        sleepJournal = SleepJournalMock.list.first(),
        onBackClick = {},
    )
}

@Preview(
    name = "Default",
    group = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DiarySleepDetailsScreenPreview_DarkMode() {
    DiarySleepDetailsScreenContent(
        sleepJournal = SleepJournalMock.list.first(),
        onBackClick = {},
    )
}

// endregion Previews
