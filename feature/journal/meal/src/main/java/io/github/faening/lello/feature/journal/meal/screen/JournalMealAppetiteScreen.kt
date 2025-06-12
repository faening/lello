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
import io.github.faening.lello.core.model.journal.AppetiteOption
import io.github.faening.lello.feature.journal.meal.JournalMealViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun JournalMealAppetiteScreen(
    viewModel: JournalMealViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenAppetiteOptionSettings: () -> Unit,
) {
    val appetiteOptions = mutableListOf<AppetiteOption>()

    LelloTheme {
        JournalMealAppetiteContainer(
            appetiteOptions = appetiteOptions,
            onAppetiteOptionToggle = { _ -> },
            onOpenAppetiteOptionSettings = onOpenAppetiteOptionSettings,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish
        )
    }
}

@Composable
private fun JournalMealAppetiteContainer(
    appetiteOptions: List<AppetiteOption>,
    onAppetiteOptionToggle: (String) -> Unit,
    onOpenAppetiteOptionSettings: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
) {
    val anySelected = appetiteOptions.any { it.selected }

    Scaffold(
        topBar = { JournalMealAppetiteTopBar(onBack) },
        bottomBar = { JournalMealAppetiteBottomBar(anySelected, onNext, onFinish) }
    ) { paddingValues ->
        JournalMealAppetiteContent(
            appetiteOptions = appetiteOptions,
            onAppetiteOptionToggle = onAppetiteOptionToggle,
            onOpenAppetiteOptionSettings = onOpenAppetiteOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalMealAppetiteTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Alimentação"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun JournalMealAppetiteBottomBar(
    enabled: Boolean,
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
private fun JournalMealAppetiteContent(
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
            text = "Qual refeição você fez?",
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
fun JournalMealAppetiteScreenPreview() {
    LelloTheme {
        JournalMealAppetiteContainer(
            appetiteOptions = mutableListOf(),
            onAppetiteOptionToggle = { _ -> },
            onOpenAppetiteOptionSettings = {},
            onBack = {},
            onNext = {},
            onFinish = {},
        )
    }
}