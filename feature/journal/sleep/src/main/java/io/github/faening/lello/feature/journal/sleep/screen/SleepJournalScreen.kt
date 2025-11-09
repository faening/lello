package io.github.faening.lello.feature.journal.sleep.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloSliderVertical
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.SleepDurationOption
import io.github.faening.lello.feature.journal.sleep.SleepJournalViewModel

@Composable
internal fun SleepJournalScreen(
    viewModel: SleepJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val context = LocalContext.current
    val sleepOptions = viewModel.sleepDurationOptions
    val selected = viewModel.currentSleepDuration.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.prepareVideo(context)
    }

    SleepJournalScreenContent(
        sleepDurationOptions = sleepOptions,
        selectedOption = selected,
        onSleepDurationChange = viewModel::toggleSleepDurationSelection,
        onBack = onBack,
        onNext = onNext
    )
}

@Composable
private fun SleepJournalScreenContent(
    sleepDurationOptions: List<SleepDurationOption>,
    selectedOption: SleepDurationOption,
    onSleepDurationChange: (SleepDurationOption) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val currentIndex = sleepDurationOptions.indexOf(selectedOption).coerceAtLeast(0)

    Scaffold(
        topBar = {
            SleepJournalTopAppBar(onBack)
        },
        bottomBar = {
            SleepJournalBottomBar(onNext)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            // Header
            Text(
                text = "Quanto tempo você dormiu?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )

            // Content
            SleepDurationSelector(
                sleepValues = sleepDurationOptions.map { it.description },
                currentIndex = currentIndex,
                onValueSelected = { index ->
                    sleepDurationOptions.getOrNull(index)?.let(onSleepDurationChange)
                }
            )
        }
    }
}

@Composable
private fun SleepJournalTopAppBar(
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
            .padding(
                start = Dimension.spacingRegular,
                end = Dimension.spacingRegular,
                bottom = Dimension.spacingRegular
            )
    ) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(LelloIcons.Outlined.ArrowRightLarge.resId),
            contentDescription = "Próximo",
            onClick = onNext
        )
    }
}

@Composable
fun SleepDurationSelector(
    sleepValues: List<String>,
    currentIndex: Int,
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
                    fontWeight = if (index == currentIndex) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.clickable { onValueSelected(index) }
                )
            }
        }
        Spacer(modifier = Modifier.width(Dimension.spacingRegular))

        // Slider vertical
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            LelloSliderVertical(
                steps = sleepValues.size,
                currentStep = currentIndex,
                enableStepDrag = true,
                onStepSelected = onValueSelected
            )
        }
        Spacer(modifier = Modifier.width(Dimension.spacingRegular))

        // Fake Space
        Column(
            modifier = Modifier.weight(1f)
        ) { }
    }
}

// region: Previews

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun SleepJournalScreenPreview_LightMode() {
    val items = SleepDurationOption.entries

    LelloTheme {
        SleepJournalScreenContent(
            sleepDurationOptions = items,
            selectedOption = SleepDurationOption.BETWEEN_6_TO_8_HOURS,
            onSleepDurationChange = {},
            onBack = {},
            onNext = {},
        )
    }
}

// endregion: Previews