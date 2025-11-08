package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.LelloSliderVertical
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel
import io.github.faening.lello.feature.journal.mood.model.MoodColorMapping
import io.github.faening.lello.feature.journal.mood.model.MoodJournalColorScheme

@Composable
internal fun MoodJournalScreen(
    viewModel: MoodJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val context = LocalContext.current
    val moodColor by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.captureEntryDateTime()
        viewModel.prepareVideo(context)
    }

    MoodJournalScreenContent(
        moodColor = moodColor,
        entryTime = entryTime,
        onBack = onBack,
        onNext = onNext,
        onMoodChange = { viewModel.updateMood(it) }
    )
}

@Composable
private fun MoodJournalScreenContent(
    moodColor: MoodColor,
    entryTime: String,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onMoodChange: (MoodColor) -> Unit
) {
    LelloTheme(moodColor = moodColor) {
        Scaffold(
            topBar = {
                MoodJournalTopAppBar(
                    entryTime = entryTime,
                    moodColor = moodColor,
                    onBack = onBack
                )
            },
            bottomBar = {
                MoodJournalBottomBar(
                    moodColor = moodColor,
                    onNext = onNext
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .padding(Dimension.spacingRegular)
            ) {
                // Header
                Text(
                    text = "Como você descreve seu humor neste momento?",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
                )

                // Content
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = Dimension.spacingExtraLarge),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MoodLabelColumn(
                        moodColor = moodColor,
                        onMoodChange = onMoodChange,
                        modifier = Modifier.weight(1f).padding(end = Dimension.spacingRegular)
                    )
                    MoodSliderColumn(
                        moodColor = moodColor,
                        onMoodChange = onMoodChange,
                        modifier = Modifier.weight(1f).padding(end = Dimension.spacingRegular)
                    )
                    MoodIconColumn(
                        onMoodChange = onMoodChange,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }

}

@Composable
private fun MoodJournalTopAppBar(
    entryTime: String,
    moodColor: MoodColor,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack),
        moodColor = moodColor
    )
}

@Composable
private fun MoodJournalBottomBar(
    moodColor: MoodColor,
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
            icon = LelloIcons.Outlined.ArrowRightLarge.imageVector,
            contentDescription = "Próximo",
            onClick = onNext,
            moodColor = moodColor
        )
    }
}

@Composable
private fun MoodLabelColumn(
    moodColor: MoodColor,
    onMoodChange: (MoodColor) -> Unit,
    modifier: Modifier = Modifier
) {
    val orderedMoods = MoodColorMapping.orderedMoods

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        orderedMoods.forEach { currentMood ->
            val moodInfo = MoodColorMapping.moodMap[currentMood]
            Text(
                text = moodInfo?.label ?: "",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (currentMood == moodColor) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.clickable { onMoodChange(currentMood) }
            )
        }
    }
}

@Composable
private fun MoodSliderColumn(
    moodColor: MoodColor,
    onMoodChange: (MoodColor) -> Unit,
    modifier: Modifier = Modifier
) {
    val orderedMoods = MoodColorMapping.orderedMoods
    val currentIndex = orderedMoods.indexOf(moodColor).coerceAtLeast(0)

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        LelloSliderVertical(
            steps = orderedMoods.size,
            currentStep = currentIndex,
            onStepSelected = { index -> onMoodChange(orderedMoods[index]) },
            enableStepDrag = true
        )
    }
}

@Composable
private fun MoodIconColumn(
    onMoodChange: (MoodColor) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoodJournalColorScheme.entries.forEach {
            Icon(
                painter = painterResource(it.iconRes),
                contentDescription = it.label,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(64.dp)
                    .clickable { onMoodChange(it.colorScheme) }
            )
        }
    }
}

// region Previews

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun MoodJournalScreenPreview_LightMode() {
    LelloTheme {
        MoodJournalScreenContent(
            moodColor = MoodColor.DEFAULT,
            entryTime = "09:41",
            onBack = {},
            onNext = {},
            onMoodChange = {}
        )
    }
}

// endregion Previews