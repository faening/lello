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
import io.github.faening.lello.core.designsystem.theme.Grey50
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.Grey700
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow500

@Composable
fun CheckInDailyCard(
    currentStep: Int, // 1..4
    modifier: Modifier = Modifier
) {
    val progressTotal = 4
    val activeColor = Yellow500
    val inactiveColor = Grey50

    Box(
        modifier = modifier.padding(bottom = Dimension.Small, end = Dimension.Small)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = Dimension.Small, y = Dimension.Small)
                .background(
                    color = Grey700.copy(alpha = Dimension.ALPHA_DISABLED),
                    shape = RoundedCornerShape(Dimension.cardRadiusLarge)
                )
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Dimension.cardRadiusLarge),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(
                width = Dimension.cardBorderStrokeWidth,
                color = Grey500
            )
        ) {
            Row(
                modifier = Modifier.padding(Dimension.Medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bonus_hexagon_10),
                    contentDescription = "Bônus de moedas",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(Dimension.Medium))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Check-In diário",
                        style = MaterialTheme.typography.titleLarge,
                        color = Grey500
                    )
                    Spacer(modifier = Modifier.height(Dimension.Small))

                    Text(
                        text = "Preencha todos os diários ao menos uma vez para ganhar 10 moedas extra",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Grey500
                    )
                    Spacer(modifier = Modifier.height(Dimension.Medium))

                    // Progress bar
                    Row {
                        repeat(progressTotal) { i ->
                            Box(
                                modifier = Modifier
                                    .height(Dimension.Small)
                                    .weight(1f)
                                    .background(
                                        color = if (i < currentStep) activeColor else inactiveColor,
                                        shape = RoundedCornerShape(Dimension.Small)
                                    )
                            )
                            if (i < progressTotal - 1) {
                                Spacer(modifier = Modifier.width(Dimension.Small))
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(
    name = "Default Color - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun CheckInDailyCardPreview() {
    LelloTheme {
        CheckInDailyCard(
            currentStep = 2
        )
    }
}