package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.HealthOptionMock
import io.github.faening.lello.core.model.journal.HealthOption
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun JournalMoodHealthScreen(
    viewModel: JournalMoodViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenHealthOptionSettings: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()
    val healthOptions by viewModel.healthOptions.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodHealthContainer(
            entryTime = entryTime,
            healthOptions = healthOptions,
            onHealthOptionToggle = viewModel::toggleHealthSelection,
            onOpenHealthOptionSettings = onOpenHealthOptionSettings,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish
        )
    }
}

@Composable
private fun JournalMoodHealthContainer(
    entryTime: String,
    healthOptions: List<HealthOption>,
    onHealthOptionToggle: (String) -> Unit,
    onOpenHealthOptionSettings: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        topBar = { JournalMoodHealthTopBar(entryTime, onBack) },
        bottomBar = { JournalMoodHealthBottomBar(onNext, onFinish) }
    ) { paddingValues ->
        JournalMoodHealthContent(
            healthOptions = healthOptions,
            onHealthOptionToggle = onHealthOptionToggle,
            onOpenHealthOptionSettings = onOpenHealthOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalMoodHealthTopBar(
    entryTime: String,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun JournalMoodHealthBottomBar(
    onNext: () -> Unit,
    onFinish: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.Medium),
        horizontalArrangement = Arrangement.spacedBy(Dimension.Medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Concluir",
            onClick = onFinish,
            modifier = Modifier.weight(1f)
        )

        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = onNext
        )
    }
}

@Composable
private fun JournalMoodHealthContent(
    healthOptions: List<HealthOption>,
    onHealthOptionToggle: (String) -> Unit,
    onOpenHealthOptionSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Percebeu alguma mudança na sua saúde hoje?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = null,
            options = healthOptions,
            isSelected = { it.selected },
            onToggle = { option -> onHealthOptionToggle(option.description) },
            onOpenSettings = onOpenHealthOptionSettings,
            getLabel = { it.description }
        )
    }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun JournalMoodStepOneScreenPreview() {
    LelloTheme {
        JournalMoodHealthContainer(
            entryTime = "12:41",
            healthOptions = HealthOptionMock.list,
            onHealthOptionToggle = { _ -> },
            onOpenHealthOptionSettings = {},
            onBack = {},
            onNext = {},
            onFinish = {}
        )
    }
}