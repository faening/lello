package io.github.faening.lello.core.designsystem.component.card

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Grey100
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.Grey700
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import java.util.Calendar
import java.util.Date

@Composable
fun DiaryCard(
    properties: DiaryCardProperties,
    dateTime: Date,
    reward: Int = 0,
    modifier: Modifier = Modifier
) {
    val formattedTime = dateTime.let {
        val cal = Calendar.getInstance().apply { time = it }
        "%02dh %02dm".format(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
    }

    Box(
        modifier = modifier.padding(bottom = Dimension.spacingSmall, end = Dimension.spacingSmall)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = Dimension.spacingSmall, y = Dimension.spacingSmall)
                .background(
                    color = Grey700.copy(alpha = Dimension.alphaStateDisabled),
                    shape = RoundedCornerShape(Dimension.cardRadiusLarge)
                )
        )

        CardContainer(
            formattedTime = formattedTime,
            reward = reward,
            properties = properties
        )
    }
}

@Composable
private fun CardContainer(
    formattedTime: String,
    reward: Int,
    properties: DiaryCardProperties
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimension.cardRadiusLarge),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(
            width = Dimension.cardBorderWidth,
            color = Grey500
        )
    ) {
        Row(
            modifier = Modifier.padding(Dimension.spacingRegular),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icone
            Image(
                painter = painterResource(id = properties.iconRes),
                contentDescription = "Diary Icon",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(Dimension.spacingRegular))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Titulo
                Text(
                    text = properties.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Grey500
                )
                Spacer(modifier = Modifier.height(Dimension.spacingSmall))

                Row {
                    // Hora
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.titleLarge,
                        color = Grey100
                    )
                    Spacer(modifier = Modifier.width(Dimension.spacingRegular))

                    // Recompensa
                    Image(
                        painter = painterResource(id = R.drawable.ic_coin),
                        contentDescription = "Moeda",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = "$reward",
                        style = MaterialTheme.typography.titleLarge,
                        color = Grey100
                    )
                }
            }

            // Arrow Icon
            Image(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

sealed class DiaryCardProperties(
    val iconRes: Int,
    val title: String
) {
    object SleepJournal : DiaryCardProperties(
        iconRes = R.drawable.ic_journal_sleep,
        title = "Diário de Sono"
    )

    object MealJournal : DiaryCardProperties(
        iconRes = R.drawable.ic_journal_meal,
        title = "Diário de Alimentação"
    )

    object MedicationJournal : DiaryCardProperties(
        iconRes = R.drawable.ic_journal_medication,
        title = "Diário de Medicamentos"
    )

    object MoodJournalSerene : DiaryCardProperties(
        iconRes = R.drawable.ic_capy_serene,
        title = "Diário de Humor"
    )

    object MoodJournalJoyful : DiaryCardProperties(
        iconRes = R.drawable.ic_capy_joyful,
        title = "Diário de Humor"
    )

    object MoodJournalBalanced : DiaryCardProperties(
        iconRes = R.drawable.ic_capy_balanced,
        title = "Diário de Humor"
    )

    object MoodJournalTroubled : DiaryCardProperties(
        iconRes = R.drawable.ic_capy_troubled,
        title = "Diário de Humor"
    )

    object MoodJournalOverwhelmed : DiaryCardProperties(
        iconRes = R.drawable.ic_capy_overwhelmed,
        title = "Diário de Humor"
    )
}

// region: Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    name = "Default Color - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun CheckInDailyCardPreview() {
    LelloTheme {
        DiaryCard(
            properties = DiaryCardProperties.SleepJournal,
            dateTime = Date(),
            reward = 10
        )
    }
}

// endregion