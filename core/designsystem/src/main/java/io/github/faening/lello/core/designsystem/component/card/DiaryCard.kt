package io.github.faening.lello.core.designsystem.component.card

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import java.util.Calendar
import java.util.Date

/**
 * Exibe um card representando um diário (sono, alimentação, humor, medicamentos), mostrando o ícone, título, horário
 * do último preenchimento e a recompensa em moedas.
 *
 * @param dateTime Data e hora do último preenchimento do diário.
 * @param reward Quantidade de moedas recebidas pelo último preenchimento do diário.
 * @param properties Propriedades específicas do tipo de diário (ícone e título).
 * @param onClick Ação a ser executada ao clicar no card.
 * @param modifier Modifier opcional para customização do layout.
 */
@Composable
fun LelloDiaryCard(
    dateTime: Date,
    reward: Int = 0,
    properties: DiaryCardOptions,
    onClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val formattedTime = dateTime.let {
        val calendar = Calendar.getInstance().apply { time = it }
        "%02dh %02dm".format(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
    }

    Box(
        modifier.padding(end = Dimension.spacingSmall, bottom = Dimension.spacingSmall)
    ) {
        // Fake Shadow
        Box(
            Modifier
                .matchParentSize()
                .offset(Dimension.spacingSmall, Dimension.spacingSmall)
                .background(
                    color = DiaryCardDefaults.shadowColor(),
                    shape = LelloShape.cardShape
                )
        )

        Card(
            modifier = Modifier.fillMaxWidth().clickable { onClick() },
            shape = LelloShape.cardShape,
            colors = DiaryCardDefaults.containerColor(),
            elevation = DiaryCardDefaults.elevation(),
            border = BorderStroke(
                width = Dimension.borderWidthThick,
                color = DiaryCardDefaults.borderColor()
            )
        ) {
            Row(
                modifier = Modifier.padding(Dimension.spacingRegular),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icone
                Image(
                    painter = painterResource(properties.iconRes),
                    contentDescription = "Ícone do diário",
                    modifier = Modifier.size(Dimension.iconSizeHuge)
                )
                Spacer(Modifier.width(Dimension.spacingRegular))

                Column(Modifier.weight(1f)) {
                    Text(
                        text = properties.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = DiaryCardDefaults.primaryTextColor(),
                        modifier = Modifier.padding(bottom = Dimension.spacingSmall)
                    )

                    Row {
                        Text(
                            text = formattedTime,
                            style = MaterialTheme.typography.titleLarge,
                            color = DiaryCardDefaults.secondaryTextColor(),
                            modifier = Modifier.padding(end = Dimension.spacingRegular)
                        )
                        Image(
                            painter = painterResource(LelloIcons.Graphic.Coin.resId),
                            contentDescription = "Moeda",
                            modifier = Modifier.size(Dimension.iconSizeSmall)
                        )
                        Spacer(modifier = Modifier.width(Dimension.spacingExtraSmall))
                        Text(
                            text = "$reward",
                            style = MaterialTheme.typography.titleLarge,
                            color = DiaryCardDefaults.secondaryTextColor(),
                        )
                    }
                }

                Image(
                    painter = painterResource(LelloIcons.Outlined.ChevronRight.resId),
                    contentDescription = "Arrow Icon",
                    modifier = Modifier.size(Dimension.iconSizeDefault)
                )
            }
        }
    }
}

sealed class DiaryCardOptions(
    val iconRes: Int,
    val title: String
) {
    object SleepJournal : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.JournalSleep.resId,
        title = "Diário de Sono"
    )

    object MealJournal : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.JournalMeal.resId,
        title = "Diário de Alimentação"
    )

    object MedicationJournal : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.JournalMedication.resId,
        title = "Diário de Remédio"
    )

    object MoodJournalSerene : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.CapybaraSerene.resId,
        title = "Diário de Humor"
    )

    object MoodJournalJoyful : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.CapybaraJoyful.resId,
        title = "Diário de Humor"
    )

    object MoodJournalBalanced : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.CapybaraBalanced.resId,
        title = "Diário de Humor"
    )

    object MoodJournalTroubled : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.CapybaraTroubled.resId,
        title = "Diário de Humor"
    )

    object MoodJournalOverwhelmed : DiaryCardOptions(
        iconRes = LelloIcons.Graphic.CapybaraOverwhelmed.resId,
        title = "Diário de Humor"
    )
}

private object DiaryCardDefaults {
    @Composable
    fun borderColor(): Color {
        return MaterialTheme.colorScheme.outline
    }

    @Composable
    fun shadowColor(): Color {
        return MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal)
    }

    @Composable
    fun elevation(): CardElevation {
        return CardDefaults.cardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
    }

    @Composable
    fun containerColor(): CardColors {
        return CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerLowest)
    }

    @Composable
    fun primaryTextColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }

    @Composable
    fun secondaryTextColor(): Color {
        return MaterialTheme.colorScheme.secondaryContainer
    }
}

@Composable
@Preview(
    name = "Sleep Journal",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_SleepJournal_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.SleepJournal
        )
    }
}

@Composable
@Preview(
    name = "Meal Journal",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_MealJournal_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.MealJournal
        )
    }
}

@Composable
@Preview(
    name = "Medication Journal",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_MedicationJournal_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.MedicationJournal
        )
    }
}

@Composable
@Preview(
    name = "Mood Journal - Serene",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_MoodJournalSerene_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.MoodJournalSerene
        )
    }
}

@Composable
@Preview(
    name = "Mood Journal - Joyful",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_MoodJournalJoyful_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.MoodJournalJoyful
        )
    }
}

@Composable
@Preview(
    name = "Mood Journal - Balanced",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_MoodJournalBalanced_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.MoodJournalBalanced
        )
    }
}

@Composable
@Preview(
    name = "Mood Journal - Troubled",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_MoodJournalTroubled_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.MoodJournalTroubled
        )
    }
}

@Composable
@Preview(
    name = "Mood Journal - Overwhelmed",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloDiaryCardPreview_MoodJournalOverwhelmed_LightMode() {
    LelloTheme {
        LelloDiaryCard(
            dateTime = Date(),
            reward = 10,
            properties = DiaryCardOptions.MoodJournalOverwhelmed
        )
    }
}
