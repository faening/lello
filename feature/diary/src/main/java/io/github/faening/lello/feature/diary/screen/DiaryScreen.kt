package io.github.faening.lello.feature.diary.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewModelScope
import io.github.faening.lello.core.designsystem.component.appbar.LelloCalendarTopAppBar
import io.github.faening.lello.core.designsystem.component.card.DiaryCardOptions
import io.github.faening.lello.core.designsystem.component.card.LelloDiaryCard
import io.github.faening.lello.core.designsystem.component.dialog.LelloAuthenticationDialog
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.MealJournalMock
import io.github.faening.lello.core.domain.mock.MoodJournalMock
import io.github.faening.lello.core.domain.mock.SleepJournalMock
import io.github.faening.lello.core.domain.util.isSameDay
import io.github.faening.lello.core.model.authentication.AuthenticationState
import io.github.faening.lello.core.model.journal.JournalType
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MedicationJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.reward.RewardOrigin
import io.github.faening.lello.feature.diary.DiaryViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel,
    onMealJournalClick: (Long) -> Unit = {},
    onMedicationJournalClick: (Long) -> Unit = {},
    onMoodJournalClick: (Long) -> Unit = {},
    onSleepJournalClick: (Long) -> Unit = {}
) {
    val activity = LocalContext.current as? FragmentActivity

    val authState by viewModel.authenticationState.collectAsState()
    val canUseBiometric by viewModel.canUseBiometricAuth.collectAsState()

    var showAuthDialog by remember { mutableStateOf(false) }
    var pendingJournalType by remember { mutableStateOf<JournalType?>(null) }
    var pendingJournalId by remember { mutableStateOf<Long?>(null) }

    val selectedDate by viewModel.selectedDate.collectAsState()
    val mealJournals by viewModel.mealJournals.collectAsState()
    val medicationJournal by viewModel.medicationJournals.collectAsState()
    val moodJournals by viewModel.moodJournals.collectAsState()
    val sleepJournals by viewModel.sleepJournals.collectAsState()

    val dayMealJournals = mealJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    val dayMedicationJournals = medicationJournal
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    val dayMoodJournals = moodJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    val daySleepJournals = sleepJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    LaunchedEffect(Unit) {
        viewModel.setSelectedDate(selectedDate)
    }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthenticationState.Success -> {
                pendingJournalId.let { id ->
                    when (pendingJournalType) {
                        JournalType.MEAL -> onMealJournalClick(id ?: 0L)
                        JournalType.MEDICATION -> onMedicationJournalClick(id ?: 0L)
                        JournalType.MOOD -> onMoodJournalClick(id ?: 0L)
                        JournalType.SLEEP -> onSleepJournalClick(id ?: 0L)
                        else -> {}
                    }
                    pendingJournalId = null
                    pendingJournalType = null
                }
                viewModel.resetAuthenticationState()
            }

            else -> {}
        }
    }

    if (showAuthDialog) {
        LelloAuthenticationDialog(
            onDismiss = {
                showAuthDialog = false
                pendingJournalId = null
                pendingJournalType = null
                viewModel.resetAuthenticationState()
            },
            onPasswordSubmit = { password ->
                viewModel.authenticateWithPassword(password)
            },
            errorMessage = (authState as? AuthenticationState.Error)?.message,
            isLoading = authState is AuthenticationState.Loading
        )

        LaunchedEffect(authState) {
            if (authState is AuthenticationState.Success ||
                authState is AuthenticationState.Error) {
                showAuthDialog = false
            }
        }
    }

    DiaryScreenContent(
        selectedDate = selectedDate,
        onSelectDate = viewModel::setSelectedDate,
        mealJournals = dayMealJournals,
        onMealJournalClick = { journalId ->
            pendingJournalId = journalId
            pendingJournalType = JournalType.MEAL

            if (canUseBiometric) {
                activity?.let {
                    viewModel.viewModelScope.launch {
                        viewModel.authenticateWithBiometric(it)
                    }
                } ?: run {
                    showAuthDialog = true
                }
            } else {
                showAuthDialog = true
            }
        },
        medicationJournals = dayMedicationJournals,
        onMedicationJournalClick = { journalId ->
            pendingJournalId = journalId
            pendingJournalType = JournalType.MEDICATION

            if (canUseBiometric) {
                activity?.let {
                    viewModel.viewModelScope.launch {
                        viewModel.authenticateWithBiometric(it)
                    }
                } ?: run {
                    showAuthDialog = true
                }
            } else {
                showAuthDialog = true
            }
        },
        moodJournals = dayMoodJournals,
        onMoodJournalClick = { journalId ->
            pendingJournalId = journalId
            pendingJournalType = JournalType.MOOD

            if (canUseBiometric) {
                activity?.let {
                    viewModel.viewModelScope.launch {
                        viewModel.authenticateWithBiometric(it)
                    }
                } ?: run {
                    showAuthDialog = true
                }
            } else {
                showAuthDialog = true
            }
        },
        sleepJournals = daySleepJournals,
        onSleepJournalClick = { journalId ->
            pendingJournalId = journalId
            pendingJournalType = JournalType.SLEEP

            if (canUseBiometric) {
                activity?.let {
                    viewModel.viewModelScope.launch {
                        viewModel.authenticateWithBiometric(it)
                    }
                } ?: run {
                    showAuthDialog = true
                }
            } else {
                showAuthDialog = true
            }
        },
        getRewardAmount = viewModel::getRewardAmount
    )
}

@Composable
private fun DiaryScreenContent(
    selectedDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit = {},
    mealJournals: List<MealJournal>,
    medicationJournals: List<MedicationJournal>,
    onMedicationJournalClick: (Long) -> Unit = {},
    onMealJournalClick: (Long) -> Unit = {},
    moodJournals: List<MoodJournal>,
    onMoodJournalClick: (Long) -> Unit = {},
    sleepJournals: List<SleepJournal>,
    onSleepJournalClick: (Long) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int = { _, _ -> 0 }
) {
    Scaffold(
        topBar = {
            DiaryScreenTopAppBar(selectedDate, onSelectDate)
        }
    ) { paddingValues ->
        if (moodJournals.isEmpty() && mealJournals.isEmpty() && sleepJournals.isEmpty()) {
            EmptyContentSection(paddingValues)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(
                        top = Dimension.spacingRegular,
                        start = Dimension.spacingRegular,
                        end = Dimension.spacingRegular
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MealJournalsSection(
                    dayMealJournals = mealJournals,
                    onMealJournalClick = onMealJournalClick,
                    getRewardAmount = getRewardAmount
                )

                MedicationJournalsSection(
                    dayMedicationJournals = medicationJournals,
                    onMedicationJournalClick = onMedicationJournalClick,
                    getRewardAmount = getRewardAmount
                )

                MoodJournalsSection(
                    dayMoodJournals = moodJournals,
                    onMoodJournalClick = onMoodJournalClick,
                    getRewardAmount = getRewardAmount
                )

                SleepJournalsSection(
                    daySleepJournals = sleepJournals,
                    onSleepJournalClick = onSleepJournalClick,
                    getRewardAmount = getRewardAmount
                )
            }
        }
    }
}

@Composable
private fun DiaryScreenTopAppBar(
    selectedDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit
) {
    LelloCalendarTopAppBar(
        selectedDate = selectedDate,
        onDateSelected = onSelectDate
    )
}

@Composable
private fun EmptyContentSection(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .padding(Dimension.spacingRegular)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Não existem registros para o período selecionado",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun MealJournalsSection(
    dayMealJournals: List<MealJournal>,
    onMealJournalClick: (Long) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int
) {
    if (dayMealJournals.isNotEmpty()) {
        dayMealJournals.forEach { journal ->
            val journalId = journal.id ?: 0L
            val reward by produceState(initialValue = 0, key1 = journalId) {
                value = getRewardAmount(RewardOrigin.MEAL_JOURNAL, journalId)
            }
            LelloDiaryCard(
                properties = DiaryCardOptions.MealJournal,
                dateTime = Date(journal.createdAt),
                reward = reward,
                onClick = { onMealJournalClick(journalId) },
                modifier = Modifier.padding(bottom = Dimension.spacingMedium)
            )
        }
    }
}

@Composable
private fun MedicationJournalsSection(
    dayMedicationJournals: List<MedicationJournal>,
    onMedicationJournalClick: (Long) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int
) {
    if (dayMedicationJournals.isNotEmpty()) {
        dayMedicationJournals.forEach { journal ->
            val journalId = journal.id ?: 0L
            val reward by produceState(initialValue = 0, key1 = journalId) {
                value = getRewardAmount(RewardOrigin.MEDICATION_JOURNAL, journalId)
            }
            LelloDiaryCard(
                properties = DiaryCardOptions.MedicationJournal,
                dateTime = Date(journal.createdAt),
                reward = reward,
                onClick = { onMedicationJournalClick(journalId) },
                modifier = Modifier.padding(bottom = Dimension.spacingMedium)
            )
        }
    }
}

@Composable
private fun MoodJournalsSection(
    dayMoodJournals: List<MoodJournal>,
    onMoodJournalClick: (Long) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int
) {
    if (dayMoodJournals.isNotEmpty()) {
        dayMoodJournals.forEach { journal ->
            val journalId = journal.id ?: 0L
            val reward by produceState(initialValue = 0, key1 = journalId) {
                value = getRewardAmount(RewardOrigin.MOOD_JOURNAL, journalId)
            }
            LelloDiaryCard(
                properties = when (journal.mood.name) {
                    "SERENE" -> DiaryCardOptions.MoodJournalSerene
                    "JOYFUL" -> DiaryCardOptions.MoodJournalJoyful
                    "BALANCED" -> DiaryCardOptions.MoodJournalBalanced
                    "TROUBLED" -> DiaryCardOptions.MoodJournalTroubled
                    "OVERWHELMED" -> DiaryCardOptions.MoodJournalOverwhelmed
                    else -> DiaryCardOptions.MoodJournalBalanced
                },
                dateTime = Date(journal.createdAt),
                reward = reward,
                onClick = { onMoodJournalClick(journalId) },
                modifier = Modifier.padding(bottom = Dimension.spacingMedium)
            )
        }
    }
}

@Composable
private fun SleepJournalsSection(
    daySleepJournals: List<SleepJournal>,
    onSleepJournalClick: (Long) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int
) {
    if (daySleepJournals.isNotEmpty()) {
        daySleepJournals.forEach { journal ->
            val journalId = journal.id ?: 0L
            val reward by produceState(initialValue = 0, key1 = journalId) {
                value = getRewardAmount(RewardOrigin.SLEEP_JOURNAL, journalId)
            }
            LelloDiaryCard(
                properties = DiaryCardOptions.SleepJournal,
                dateTime = Date(journal.createdAt),
                reward = reward,
                onClick = { onSleepJournalClick(journalId) },
                modifier = Modifier.padding(bottom = Dimension.spacingMedium)
            )
        }
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
private fun DiaryScreenPreview_LightMode_Default() {
    LelloTheme {
        DiaryScreenContent(
            selectedDate = LocalDate.now(),
            mealJournals = MealJournalMock.list.take(2),
            medicationJournals = emptyList(),
            moodJournals = MoodJournalMock.list.take(2),
            sleepJournals = SleepJournalMock.list.take(2)
        )
    }
}

@Preview(
    name = "Empty",
    group = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun DiaryScreenPreview_LightMode_Empty() {
    LelloTheme {
        DiaryScreenContent(
            selectedDate = LocalDate.now(),
            mealJournals = emptyList(),
            medicationJournals = emptyList(),
            moodJournals = emptyList(),
            sleepJournals = emptyList()
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
private fun DiaryScreenPreview_DarkMode_Default() {
    LelloTheme {
        DiaryScreenContent(
            selectedDate = LocalDate.now(),
            mealJournals = MealJournalMock.list.take(2),
            medicationJournals = emptyList(),
            moodJournals = MoodJournalMock.list.take(2),
            sleepJournals = SleepJournalMock.list.take(2)
        )
    }
}

@Preview(
    name = "Empty",
    group = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DiaryScreenPreview_DarkMode_Empty() {
    LelloTheme {
        DiaryScreenContent(
            selectedDate = LocalDate.now(),
            mealJournals = emptyList(),
            medicationJournals = emptyList(),
            moodJournals = emptyList(),
            sleepJournals = emptyList()
        )
    }
}

// endregion Previews