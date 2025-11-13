package io.github.faening.lello.core.designsystem.component.card

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

/**
 * Exibe um card de check-in diário, mostrando o progresso do usuário no preenchimento dos diários e uma mensagem de
 * incentivo ou parabéns.
 *
 * @param currentStep Quantos diários já foram preenchidos. O App conta com 4 diários, logo, o máximo é 4.
 * @param totalSteps Quantidade máxima de diários. Atualmente, é 4.
 * @param done Se preencheu todos os diários, ganha as moedas extra. Se isso realmente aconteceu, done vira true e muda
 * a mensagem.
 * @param modifier Modifier opcional para customização do layout.
 */
@Composable
fun LelloCheckInDailyCard(
    currentStep: Int = 1,
    totalSteps: Int = 4,
    done: Boolean = false,
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
                    color = CheckInDailyCardDefaults.shadowColor(),
                    shape = LelloShape.cardShape
                )
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = LelloShape.cardShape,
            colors = CheckInDailyCardDefaults.containerColor(),
            elevation = CheckInDailyCardDefaults.elevation(),
            border = BorderStroke(
                width = Dimension.borderWidthThick,
                color = CheckInDailyCardDefaults.borderColor()
            )
        ) {
            Row(
                modifier = Modifier.padding(Dimension.spacingRegular),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(LelloIcons.Graphic.BonusHexagon.resId),
                    contentDescription = "Bônus de moedas",
                    modifier = Modifier.size(Dimension.heightButtonLarge)
                )
                Spacer(Modifier.width(Dimension.spacingSmall))

                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Check-In diário",
                        style = MaterialTheme.typography.titleLarge,
                        color = CheckInDailyCardDefaults.primaryTextColor(),
                        modifier = Modifier.padding(bottom = Dimension.spacingSmall)
                    )
                    Text(
                        text = if (done) {
                            "Parabéns! Você adquiriu moedas extra hoje."
                        } else {
                            "Preencha todos os diários uma vez ao dia para ganhar 10 moedas extra."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = CheckInDailyCardDefaults.secondaryTextColor(),
                        modifier = Modifier.padding(bottom = Dimension.spacingRegular)
                    )

                    // Progress Bar
                    Row {
                        repeat(totalSteps) { i ->
                            Box(
                                Modifier
                                    .height(Dimension.spacingSmall)
                                    .weight(1f)
                                    .background(
                                        color = if (i < currentStep) {
                                            CheckInDailyCardDefaults.selectedItemColor()
                                        } else {
                                            CheckInDailyCardDefaults.unselectedItemColor()
                                        },
                                        shape = LelloShape.pillShape
                                    )
                            )
                            if (i < totalSteps - 1) {
                                Spacer(Modifier.width(Dimension.spacingSmall))
                            }
                        }
                    }
                }
            }
        }
    }
}

private object CheckInDailyCardDefaults {
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
    fun selectedItemColor(): Color {
        return MaterialTheme.colorScheme.primary
    }

    @Composable
    fun unselectedItemColor(): Color {
        return MaterialTheme.colorScheme.onSecondary
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
        return MaterialTheme.colorScheme.onPrimaryContainer
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    name = "In Progress",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloCheckInDailyCardPreview_InProgress_LightMode() {
    LelloTheme {
        LelloCheckInDailyCard(
            currentStep = 2
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    name = "Done",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloCheckInDailyCardPreview_Done_LightMode() {
    LelloTheme {
        LelloCheckInDailyCard(
            currentStep = 4,
            done = true
        )
    }
}
