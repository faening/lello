package io.github.faening.lello.feature.home.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.appbar.LelloImageTopAppBar
import io.github.faening.lello.core.designsystem.component.card.CheckInDailyCard
import io.github.faening.lello.core.designsystem.component.card.JournalCategoryCard
import io.github.faening.lello.core.designsystem.component.card.JournalCategoryCardConfig
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.JournalCategoryMock
import io.github.faening.lello.core.model.journal.JournalBonusState
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.reward.DailyCheckInState
import io.github.faening.lello.feature.home.HomeViewModel
import io.github.faening.lello.feature.journal.meal.JournalMealDestinations
import io.github.faening.lello.feature.journal.medication.navigation.JournalMedicationDestinations
import io.github.faening.lello.feature.journal.mood.MoodJournalDestinations
import io.github.faening.lello.feature.journal.sleep.SleepJournalDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToModule: (String) -> Unit
) {
    val journalCategories by viewModel.journalCategories.collectAsState()
    val bonusState by viewModel.journalBonusState.collectAsState()
    val checkInState by viewModel.dailyCheckInState.collectAsState()

    HomeScreenContainer(
        journalCategories = journalCategories,
        bonusState = bonusState,
        checkInState = checkInState,
        onNavigateToModule = onNavigateToModule
    )
}

@Composable
private fun HomeScreenContainer(
    journalCategories: List<JournalCategory>,
    bonusState: JournalBonusState,
    checkInState: DailyCheckInState,
    onNavigateToModule: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    LelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LelloImageTopAppBar(moodColor = MoodColor.INVERSE)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = Dimension.paddingComponentMedium,
                        end = Dimension.paddingComponentMedium,
                        start = Dimension.paddingComponentMedium
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CheckInDailyCard(
                        currentStep = checkInState.currentStep,
                        subtitle = if (checkInState.bonusReceived) {
                            "Parabéns! Você adquiriu moedas extra hoje."
                        } else {
                            "Preencha todos os diários para ganhar 10 moedas extra"
                        }
                    )
                    Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))
                    Text(
                        text = "Meus diários",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = Dimension.spacingRegular)
                    )
                    if (journalCategories.isEmpty()) {
                        Text("Carregando...")
                    } else {
                        journalCategories.forEach { category ->
                            val badgeText = when (category.name) {
                                "Humor" -> formatToHourMinute(bonusState.moodRemaining)
                                "Sono" -> formatToHourMinute(bonusState.sleepRemaining)
                                "Medicamentos" -> formatToHourMinute(bonusState.medicationRemaining)
                                "Alimentação" -> formatToHourMinute(bonusState.mealRemaining)
                                else -> ""
                            }

                            JournalCategoryCard(
                                title = category.name,
                                description = category.shortDescription,
                                badgeText = badgeText,
                                configuration = JournalCategoryCardConfig.fromName(category.name),
                                onClick = {
                                    when (category.name) {
                                        "Humor" -> onNavigateToModule(MoodJournalDestinations.GRAPH)
                                        "Sono" -> onNavigateToModule(SleepJournalDestinations.GRAPH)
                                        "Medicamentos" -> onNavigateToModule(JournalMedicationDestinations.GRAPH)
                                        "Alimentação" -> onNavigateToModule(JournalMealDestinations.GRAPH)
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(Dimension.spacingMedium))
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
private fun formatToHourMinute(millis: Long): String {
    val totalMinutes = (millis / 1000 / 60).coerceAtLeast(0)
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return String.format("%dh %02dm", hours, minutes)
}

@Preview("HomeScreen - Light Theme")
@Composable
private fun PreviewHomeScreen() {
    val bonusState = JournalBonusState(
        moodRemaining = 3600000,
        sleepRemaining = 3600000,
        medicationRemaining = 3600000,
        mealRemaining = 3600000
    )
    val checkInState = DailyCheckInState(
        currentStep = 2,
        bonusReceived = false
    )

    HomeScreenContainer(
        journalCategories = JournalCategoryMock.list,
        bonusState = bonusState,
        checkInState = checkInState,
        onNavigateToModule = {}
    )
}
