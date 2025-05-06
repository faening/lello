package io.github.faening.lello.core.designsystem.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.Blue100
import io.github.faening.lello.core.designsystem.theme.Blue300
import io.github.faening.lello.core.designsystem.theme.Blue700
import io.github.faening.lello.core.designsystem.theme.Blue900
import io.github.faening.lello.core.designsystem.theme.Green100
import io.github.faening.lello.core.designsystem.theme.Green300
import io.github.faening.lello.core.designsystem.theme.Green700
import io.github.faening.lello.core.designsystem.theme.Green900
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.Grey700
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow100
import io.github.faening.lello.core.designsystem.theme.Yellow500
import io.github.faening.lello.core.designsystem.theme.Yellow700
import io.github.faening.lello.core.designsystem.theme.Yellow800
import io.github.faening.lello.core.designsystem.theme.Yellow900
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

        // Card
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
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = configuration.titleColor,
                        maxLines = 1,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = configuration.textColor,
                        minLines = 3,
                        maxLines = 3,
                    )
                }

                if (badgeText != null) {
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
        cardBackgroundColor = Blue300,
        badgeBackgroundColor = Blue100,
        badgeTextColor = Blue700,
        titleColor = Blue900,
        textColor = Blue700,
        iconRes = designsystemR.drawable.ic_journal_mood
    )

    object Sleep : JournalCategoryCardConfig(
        cardBackgroundColor = Blue300,
        badgeBackgroundColor = Blue100,
        badgeTextColor = Blue700,
        titleColor = Blue900,
        textColor = Blue700,
        iconRes = designsystemR.drawable.ic_journal_sleep
    )

    object Meal : JournalCategoryCardConfig(
        cardBackgroundColor = Yellow500,
        badgeBackgroundColor = Yellow100,
        badgeTextColor = Yellow700,
        titleColor = Yellow900,
        textColor = Yellow800,
        iconRes = designsystemR.drawable.ic_journal_meal
    )

    object Medication : JournalCategoryCardConfig(
        cardBackgroundColor = Green300,
        badgeBackgroundColor = Green100,
        badgeTextColor = Green700,
        titleColor = Green900,
        textColor = Green700,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(name = "Journa Mood - Light Theme", showBackground = true)
private fun PreviewJournalMoodCard() {
    LelloTheme {
        JournalCategoryCard(
            title = "Diário de Humor",
            description = "Registre suas emoções e compreenda melhor seus estados emocionais ao longo do dia",
            badgeText = null,
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
            title = "Diário de Medicamentos",
            description = "Acompanhe sua medicação diária e monitore sua adaptação ao tratamento",
            badgeText = null,
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
            title = "Diário de Sono",
            description = "Monitore seu sono e descubra como ele impacta seu bem-estar emocional",
            badgeText = null,
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
            title = "Diário de Sono",
            description = "Registre sua alimentação e compreenda como ela afeta sua saúde emocional",
            badgeText = null,
            configuration = JournalCategoryCardConfig.fromName("Alimentação"),
            onClick = {}
        )
    }
}