package io.github.faening.lello.feature.journal.medication.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.medication.MedicationJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
fun MedicationJournalSummaryScreen(
    viewModel: MedicationJournalViewModel,
    onExit: () -> Unit
) {

    MedicationJournalSummaryContent(
        onExit = onExit
    )
}

@Composable
private fun MedicationJournalSummaryContent(
    onExit: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = designsystemR.drawable.img_journal_summary),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = { MedicationJournalSummaryBottomBar(onExit) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimension.spacingRegular),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { }
        }
    }
}

@Composable
private fun MedicationJournalSummaryBottomBar(
    onExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular)
    ) {
        LelloFilledButton(
            label = "Sair",
            onClick = onExit
        )
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
private fun MedicationJournalSummaryScreenPreview_LightMode() {
    LelloTheme {
        MedicationJournalSummaryContent(
            onExit = {}
        )
    }
}

// endregion Previews