package io.github.faening.lello.core.designsystem.component.card

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Blue100
import io.github.faening.lello.core.designsystem.theme.Blue300
import io.github.faening.lello.core.designsystem.theme.Blue700
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Green100
import io.github.faening.lello.core.designsystem.theme.Green300
import io.github.faening.lello.core.designsystem.theme.Green700
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow100
import io.github.faening.lello.core.designsystem.theme.Yellow300
import io.github.faening.lello.core.designsystem.theme.Yellow700
import io.github.faening.lello.core.model.journal.JournalType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalCategoryCard(
    type: JournalType,
    description: String = "",
    badgeText: String,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Box(
        modifier.padding(end = Dimension.spacingSmall, bottom = Dimension.spacingSmall)
    ) {
        // Fake Shadow
        Box(
            Modifier
                .matchParentSize()
                .offset(Dimension.spacingSmall, Dimension.spacingSmall)
                .background(
                    color = JournalCategoryCardDefaults.shadowColor(type),
                    shape = RoundedCornerShape(Dimension.cardRadiusLarge)
                )
        )

        Card(
            modifier = Modifier
                .height(108.dp)
                .fillMaxWidth()
                .clickable { onClick() },
            shape = RoundedCornerShape(Dimension.cardRadiusLarge),
            colors = JournalCategoryCardDefaults.containerColor(type),
            elevation = JournalCategoryCardDefaults.elevation(),
            border = BorderStroke(
                width = Dimension.borderWidthThick,
                color = JournalCategoryCardDefaults.borderColor()
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimension.spacingRegular),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Icon
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = JournalCategoryCardDefaults.getIconResource(type),
                        contentDescription = "Ícone do card de categoria de diário",
                        modifier = Modifier.size(Dimension.iconSizeHuge),
                    )
                }
                Spacer(modifier = Modifier.width(Dimension.spacingSmall))

                Column(Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = JournalType.getName(type),
                            style = MaterialTheme.typography.titleLarge,
                            color = JournalCategoryCardDefaults.primaryTextColor(),
                            maxLines = 1,
                            modifier = Modifier
                                .weight(2f)
                                .padding(end = Dimension.spacingSmall)
                        )

                        Box(
                            Modifier
                                .weight(1f)
                                .background(
                                    color = JournalCategoryCardDefaults.badgeContainerColor(type),
                                    shape = LelloShape.badge
                                )
                                .padding(
                                    horizontal = Dimension.spacingSmall,
                                    vertical = Dimension.spacingSmall
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = badgeText,
                                style = MaterialTheme.typography.labelMedium,
                                color = JournalCategoryCardDefaults.badgeTextColor(type),
                                maxLines = 1
                            )
                        }
                    }
                    Spacer(Modifier.height(Dimension.spacingSmall))

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = JournalCategoryCardDefaults.primaryTextColor(),
                        maxLines = 2,
                    )
                }
            }
        }
    }
}

private object JournalCategoryCardDefaults {
    @Composable
    fun borderColor(): Color {
        return MaterialTheme.colorScheme.outline
    }

    @Composable
    fun shadowColor(type: JournalType): Color {
        return when (type) {
            JournalType.MOOD, JournalType.SLEEP -> Blue700.copy(Dimension.alphaStateNormal)
            JournalType.MEAL -> Yellow700.copy(Dimension.alphaStateNormal)
            JournalType.MEDICATION -> Green700.copy(Dimension.alphaStateNormal)
        }
    }

    @Composable
    fun elevation(): CardElevation {
        return CardDefaults.cardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
    }

    @Composable
    fun containerColor(category: JournalType): CardColors {
        return when (category) {
            JournalType.MOOD, JournalType.SLEEP -> CardDefaults.cardColors(Blue100)
            JournalType.MEAL -> CardDefaults.cardColors(Yellow100)
            JournalType.MEDICATION -> CardDefaults.cardColors(Green100)
        }
    }

    @Composable
    fun badgeContainerColor(category: JournalType): Color {
        return when (category) {
            JournalType.MOOD, JournalType.SLEEP -> Blue300
            JournalType.MEAL -> Yellow300
            JournalType.MEDICATION -> Green300
        }
    }

    @Composable
    fun badgeTextColor(category: JournalType): Color {
        return when (category) {
            JournalType.MOOD, JournalType.SLEEP -> Blue700
            JournalType.MEAL -> Yellow700
            JournalType.MEDICATION -> Green700
        }
    }

    @Composable
    fun primaryTextColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }

    @Composable
    fun getIconResource(type: JournalType): Painter {
        val icon = when (type) {
            JournalType.MOOD -> LelloIcons.Graphic.JournalMood.resId
            JournalType.SLEEP -> LelloIcons.Graphic.JournalSleep.resId
            JournalType.MEDICATION -> LelloIcons.Graphic.JournalMedication.resId
            JournalType.MEAL -> LelloIcons.Graphic.JournalMeal.resId
        }
        return painterResource(icon)
    }
}

@Composable
@Preview(
    name = "Journal Mood",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun JournalCategoryCardPreview_JournalMood_LightMode() {
    LelloTheme {
        JournalCategoryCard(
            type = JournalType.MOOD,
            description = "Registre suas emoções e compreenda melhor seus estados emocionais.",
            badgeText = "1h 16m",
            onClick = {}
        )
    }
}

@Composable
@Preview(
    name = "Journal Medication",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun JournalCategoryCardPreview_JournalMedication_LightMode() {
    LelloTheme {
        JournalCategoryCard(
            type = JournalType.MEDICATION,
            description = "Acompanhe sua medicação diária e monitore sua adaptação ao tratamento.",
            badgeText = "1h 16m",
            onClick = {}
        )
    }
}

@Composable
@Preview(
    name = "Journal Sleep",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun JournalCategoryCardPreview_JournalSleep_LightMode() {
    LelloTheme {
        JournalCategoryCard(
            type = JournalType.SLEEP,
            description = "Monitore seu sono e descubra como ele impacta seu bem-estar emocional.",
            badgeText = "1h 16m",
            onClick = {}
        )
    }
}

@Composable
@Preview(
    name = "Journal Meal",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun JournalCategoryCardPreview_JournalMeal_LightMode() {
    LelloTheme {
        JournalCategoryCard(
            type = JournalType.MEAL,
            description = "Registre sua alimentação e compreenda como ela afeta sua saúde emocional.",
            badgeText = "1h 16m",
            onClick = {}
        )
    }
}
