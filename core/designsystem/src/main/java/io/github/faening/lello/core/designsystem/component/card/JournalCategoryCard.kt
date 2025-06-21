package io.github.faening.lello.core.designsystem.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import io.github.faening.lello.core.designsystem.theme.Blue100
import io.github.faening.lello.core.designsystem.theme.Blue300
import io.github.faening.lello.core.designsystem.theme.Blue700
import io.github.faening.lello.core.designsystem.theme.Green100
import io.github.faening.lello.core.designsystem.theme.Green300
import io.github.faening.lello.core.designsystem.theme.Green700
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.Grey700
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow100
import io.github.faening.lello.core.designsystem.theme.Yellow300
import io.github.faening.lello.core.designsystem.theme.Yellow700
import io.github.faening.lello.core.designsystem.R as designsystemR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalCategoryCard(
    title: String,
    description: String,
    badgeText: String? = null,
    configuration: JournalCategoryCardConfig,
    onClick: () -> Unit
) {
    val radius = 8.dp

    Box(
        modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = 8.dp, y = 8.dp)
                .background(
                    color = Grey700.copy(alpha = 0.25f),
                    shape = RoundedCornerShape(radius)
                )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = configuration.cardBackgroundColor),
            shape = RoundedCornerShape(radius),
            border = BorderStroke(2.dp, Grey500),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = configuration.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            color = configuration.titleColor,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                        if (badgeText != null) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = configuration.badgeBackgroundColor,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = badgeText,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = configuration.badgeTextColor
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = configuration.textColor,
                        maxLines = 2,
                    )
                }
            }
        }
    }
}

sealed class JournalCategoryCardConfig(
    val cardBackgroundColor: Color,
    val badgeBackgroundColor: Color,
    val badgeTextColor: Color,
    val titleColor: Color,
    val textColor: Color,
    val iconRes: Int
) {
    object Mood : JournalCategoryCardConfig(
        cardBackgroundColor = Blue100,
        badgeBackgroundColor = Blue300,
        badgeTextColor = Blue700,
        titleColor = Grey500,
        textColor = Grey500,
        iconRes = designsystemR.drawable.ic_journal_mood
    )

    object Sleep : JournalCategoryCardConfig(
        cardBackgroundColor = Blue100,
        badgeBackgroundColor = Blue300,
        badgeTextColor = Blue700,
        titleColor = Grey500,
        textColor = Grey500,
        iconRes = designsystemR.drawable.ic_journal_sleep
    )

    object Meal : JournalCategoryCardConfig(
        cardBackgroundColor = Yellow100,
        badgeBackgroundColor = Yellow300,
        badgeTextColor = Yellow700,
        titleColor = Grey500,
        textColor = Grey500,
        iconRes = designsystemR.drawable.ic_journal_meal
    )

    object Medication : JournalCategoryCardConfig(
        cardBackgroundColor = Green100,
        badgeBackgroundColor = Green300,
        badgeTextColor = Green700,
        titleColor = Grey500,
        textColor = Grey500,
        iconRes = designsystemR.drawable.ic_journal_medication
    )

    companion object {
        fun fromName(name: String): JournalCategoryCardConfig {
            return when (name) {
                in listOf("Diário de Humor", "Humor") -> Mood
                in listOf("Diário de Sono", "Sono") -> Sleep
                in listOf("Diário de Alimentação", "Alimentação") -> Meal
                in listOf("Diário de Medicamentos", "Medicamentos") -> Medication
                else -> Mood
            }
        }
    }
}

// region: Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(name = "Journa Mood - Light Theme", showBackground = true)
private fun PreviewJournalMoodCard() {
    LelloTheme {
        JournalCategoryCard(
            title = "Humor",
            description = "Registre suas emoções e compreenda melhor seus estados emocionais ao longo do dia",
            badgeText = "1h 16m",
            configuration = JournalCategoryCardConfig.fromName("Humor"),
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(name = "Journa Medication - Light Theme", showBackground = true)
private fun PreviewJournalMedicationCard() {
    LelloTheme {
        JournalCategoryCard(
            title = "Medicamentos",
            description = "Acompanhe sua medicação diária e monitore sua adaptação ao tratamento",
            badgeText = "1h 16m",
            configuration = JournalCategoryCardConfig.fromName("Medicamentos"),
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(name = "Journa Sleep - Light Theme", showBackground = true)
private fun PreviewJournalSleepCard() {
    LelloTheme {
        JournalCategoryCard(
            title = "Sono",
            description = "Monitore seu sono e descubra como ele impacta seu bem-estar emocional",
            badgeText = "1h 16m",
            configuration = JournalCategoryCardConfig.fromName("Sono"),
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(name = "Journa Meal - Light Theme", showBackground = true)
private fun PreviewJournalMealCard() {
    LelloTheme {
        JournalCategoryCard(
            title = "Alimentação",
            description = "Registre sua alimentação e compreenda como ela afeta sua saúde emocional",
            badgeText = "1h 16m",
            configuration = JournalCategoryCardConfig.fromName("Alimentação"),
            onClick = {}
        )
    }
}

// endregion