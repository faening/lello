package io.github.faening.lello.feature.journal.meal.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.AppetiteOptionMock
import io.github.faening.lello.core.model.option.AppetiteOption
import io.github.faening.lello.feature.journal.meal.MealJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun MealJournalAppetiteScreen(
    viewModel: MealJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenAppetiteOptionSettings: () -> Unit,
) {
    val appetiteOptions by viewModel.appetiteOptions.collectAsState()

    LelloTheme {
        MealJournalAppetiteContainer(
            appetiteOptions = appetiteOptions,
            onAppetiteOptionToggle = viewModel::toggleAppetiteSelection,
            onOpenAppetiteOptionSettings = onOpenAppetiteOptionSettings,
            onSave = viewModel::saveMealJournal,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish
        )
    }
}

@Composable
private fun MealJournalAppetiteContainer(
    appetiteOptions: List<AppetiteOption>,
    onAppetiteOptionToggle: (String) -> Unit,
    onOpenAppetiteOptionSettings: () -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
) {
    val anySelected = appetiteOptions.any { it.selected }

    Scaffold(
        topBar = { MealJournalAppetiteTopBar(onBack) },
        bottomBar = {
            MealJournalAppetiteBottomBar(
            enabled = anySelected,
            onNext = onNext,
            onSave = onSave,
            onFinish = onFinish)

        }
    ) { paddingValues ->
        MealJournalAppetiteContent(
            appetiteOptions = appetiteOptions,
            onAppetiteOptionToggle = onAppetiteOptionToggle,
            onOpenAppetiteOptionSettings = onOpenAppetiteOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun MealJournalAppetiteTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Alimentação"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun MealJournalAppetiteBottomBar(
    enabled: Boolean,
    onSave: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
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
            enabled = enabled,
            onClick = {
                onSave()
                onFinish()
            },
            modifier = Modifier.weight(1f)
        )

        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = enabled,
            onClick = onNext
        )
    }
}

@Composable
private fun MealJournalAppetiteContent(
    appetiteOptions: List<AppetiteOption>,
    onAppetiteOptionToggle: (String) -> Unit,
    onOpenAppetiteOptionSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Como estava seu apetite?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = null,
            options = appetiteOptions,
            isSelected = { it.selected },
            onToggle = { option -> onAppetiteOptionToggle(option.description) },
            onOpenSettings = onOpenAppetiteOptionSettings,
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
fun MealJournalAppetiteScreenPreview() {
    LelloTheme {
        MealJournalAppetiteContainer(
            appetiteOptions = AppetiteOptionMock.list,
            onAppetiteOptionToggle = { _ -> },
            onOpenAppetiteOptionSettings = {},
            onSave = {},
            onBack = {},
            onNext = {},
            onFinish = {},
        )
    }
}