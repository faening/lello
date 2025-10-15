package io.github.faening.lello.feature.journal.sleep.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloSliderVertical
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.SleepDurationOption
import io.github.faening.lello.feature.journal.sleep.SleepJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun SleepJournalScreen(
    viewModel: SleepJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val sleepOptions = viewModel.sleepDurationOptions
    val selected = viewModel.currentSleepDuration.collectAsState().value

    LelloTheme {
        SleepJournalContainer(
            sleepDurationOptions = sleepOptions,
            selectedOption = selected,
            onSleepDurationChange = viewModel::toggleSleepDurationSelection,
            onBack = onBack,
            onNext = onNext
        )
    }
}

@Composable
private fun SleepJournalContainer(
    sleepDurationOptions: List<SleepDurationOption>,
    selectedOption: SleepDurationOption,
    onSleepDurationChange: (SleepDurationOption) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    Scaffold(
        topBar = { SleepJournalTopBar(onBack) },
        bottomBar = { SleepJournalBottomBar(onNext) }
    ) { paddingValues ->
        SleepJournalContent(
            sleepDurationOptions = sleepDurationOptions,
            selectedOption = selectedOption,
            onSleepDurationChange = onSleepDurationChange,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun SleepJournalTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Sono"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun SleepJournalBottomBar(
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .padding(Dimension.spacingRegular)
    ) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(LelloIcons.Outlined.ArrowRightLarge.resId),
            contentDescription = "Próximo",
            onClick = onNext
        )
    }
}

@Composable
private fun SleepJournalContent(
    sleepDurationOptions: List<SleepDurationOption>,
    selectedOption: SleepDurationOption,
    onSleepDurationChange: (SleepDurationOption) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = sleepDurationOptions.indexOf(selectedOption).coerceAtLeast(0)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(Dimension.spacingRegular)
    ) {
        Text(
            text = "Quanto tempo você dormiu?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))
        SleepDurationSelector(
            sleepValues = sleepDurationOptions.map { it.description },
            selectedIndex = selectedIndex,
            onValueSelected = { index ->
                sleepDurationOptions.getOrNull(index)?.let(onSleepDurationChange)
            }
        )
    }
}

@Composable
fun SleepDurationSelector(
    sleepValues: List<String>,
    selectedIndex: Int,
    onValueSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Labels
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.End
        ) {
            sleepValues.forEachIndexed { index, label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (index == selectedIndex) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Slider vertical
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            LelloSliderVertical(
                steps = sleepValues.size,
                currentStep = selectedIndex,
                enableStepDrag = true,
                onStepSelected = onValueSelected
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Fake Space
        Column(
            modifier = Modifier.weight(1f)
        ) { }
    }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun SleepJournalScreenPreview() {
    LelloTheme {
        SleepJournalContainer(
            sleepDurationOptions = emptyList(),
            selectedOption = SleepDurationOption.BETWEEN_6_TO_8_HOURS,
            onSleepDurationChange = {},
            onBack = {},
            onNext = {},
        )
    }
}