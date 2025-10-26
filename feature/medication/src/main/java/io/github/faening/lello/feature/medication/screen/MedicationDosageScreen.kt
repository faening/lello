package io.github.faening.lello.feature.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.feature.medication.MedicationViewModel

@Composable
fun MedicationDosageScreen(
    viewModel: MedicationViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit,
) {
    val dosageForms by viewModel.dosageFormOptions.collectAsState()

    MedicationDosageScreenContainer(
        dosageForms = dosageForms,
        onBack = onBack,
        onSave = onFinish
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MedicationDosageScreenContainer(
    moodColor: MoodColor = MoodColor.DEFAULT,
    dosageForms: List<MedicationDosageFormOption>,
    onBack: () -> Unit,
    onSave: () -> Unit,
) {
    Scaffold(
        topBar = {
            MedicationDosageTopAppBar(
                onBack = onBack
            )
        },
        bottomBar = {
            MedicationDosageBottomBar(
                moodColor = moodColor,
                onSave = onSave
            )
        }
    ) { paddingValues ->
        MedicationDosageContent(
            moodColor = moodColor,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun MedicationDosageTopAppBar(
    moodColor: MoodColor = MoodColor.INVERSE,
    onBack: () -> Unit,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Remédios"),
        navigateUp = TopAppBarAction(onClick = onBack),
        moodColor = moodColor
    )
}

@Composable
private fun MedicationDosageBottomBar(
    moodColor: MoodColor,
    onSave: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFloatingActionButton(
            icon = LelloIcons.Outlined.ArrowRightLarge.imageVector,
            contentDescription = "Próximo",
            moodColor = moodColor,
            onClick = onSave
        )
    }
}

@Composable
private fun MedicationDosageContent(
    moodColor: MoodColor,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular)
    ) {

    }
}

// region Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun MedicationDosageScreenPreview_LightMode() {
    LelloTheme {
        MedicationDosageScreenContainer(
            moodColor = MoodColor.DEFAULT,
            dosageForms = emptyList(),
            onBack = {},
            onSave = {}
        )
    }
}

// endregion Previews