package io.github.faening.lello.feature.diary.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow700
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
    Scaffold(
        topBar = {
            DiarySleepDetailsTopAppBar(onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimension.spacingMedium)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(LelloIcons.Graphic.DiarySleep.resId),
                    contentDescription = "Diary Medication Cover Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
            }

            DiarySleepDetailsCard(sleepJournal = sleepJournal)
        }
    }
}

@Composable
private fun DiarySleepDetailsTopAppBar(
    onBackClick: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Diário de Sono"),
        navigateUp = TopAppBarAction(onClick = onBackClick),
    )
}

@Composable
private fun DiarySleepDetailsCard(
    sleepJournal: SleepJournal
) {
    val shape = RoundedCornerShape(
        topStart = Dimension.borderRadiusLarge,
        topEnd = Dimension.borderRadiusLarge,
        bottomStart = Dimension.borderRadiusLarge,
        bottomEnd = Dimension.borderRadiusLarge
    )
    val containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
    val shadowOffset = Dimension.spacingSmall

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = shadowOffset, bottom = shadowOffset)
    ) {
        // Fake Shadow
        Box(
            Modifier
                .matchParentSize()
                .offset(shadowOffset, shadowOffset)
                .background(
                    color = MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal),
                    shape = shape
                )
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = shape,
            border = BorderStroke(
                width = Dimension.borderWidthThick,
                color = MaterialTheme.colorScheme.outline
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimension.spacingRegular),
                verticalArrangement = Arrangement.spacedBy(Dimension.spacingRegular)
            ) {
                DetailSection(
                    title = "Quanto tempo você dormiu?",
                    content = {
                        Text(
                            text = sleepJournal.sleepDuration.description.uppercase().ifEmpty { "-" },
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )

                DetailSection(
                    title = "Como você se sentiu ao acordar?",
                    content = {
                        val text = sleepJournal.sleepSensationOptions
                            .map { it.description.trim() }
                            .filter { it.isNotEmpty() }
                            .joinToString(separator = ", ") { it.uppercase() }
                            .ifEmpty { "-" }

                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )

                DetailSection(
                    title = "Hora registro",
                    content = {
                        val formatter = DateTimeFormatter.ofPattern("HH'h' mm'm'")
                        Text(
                            text = formatter.format(sleepJournal.createdAt.toLocalDateTime()) ?: "_",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )

                DetailSection(
                    title = "Como foi a qualidade do seu sono?",
                    content = {
                        val text = sleepJournal.sleepQualityOptions
                            .map { it.description.trim() }
                            .filter { it.isNotEmpty() }
                            .joinToString(separator = ", ") { it.uppercase() }
                            .ifEmpty { "-" }

                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )

                DetailSection(
                    title = "Quanto tempo acordado antes de dormir?",
                    content = {
                        Text(
                            text = "${sleepJournal.sleeplessTime} MINUTOS".ifEmpty { "-" },
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )

                DetailSection(
                    title = "O que você fez antes de dormir?",
                    content = {
                        val text = sleepJournal.sleepActivityOptions
                            .map { it.description.trim() }
                            .filter { it.isNotEmpty() }
                            .joinToString(separator = ", ") { it.uppercase() }
                            .ifEmpty { "-" }

                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )

                DetailSection(
                    title = "Onde você dormiu?",
                    content = {
                        val text = sleepJournal.locationOptions
                            .map { it.description.trim() }
                            .filter { it.isNotEmpty() }
                            .joinToString(separator = ", ") { it.uppercase() }
                            .ifEmpty { "-" }

                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun DetailSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium,
                color = Yellow700
            )
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
    LelloTheme {
        DiarySleepDetailsScreenContent(
            sleepJournal = SleepJournalMock.list.first(),
            onBackClick = {},
        )
    }
}

@Preview(
    name = "Default",
    group = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DiarySleepDetailsScreenPreview_DarkMode() {
    LelloTheme {
        DiarySleepDetailsScreenContent(
            sleepJournal = SleepJournalMock.list.first(),
            onBackClick = {},
        )
    }
}

// endregion Previews
