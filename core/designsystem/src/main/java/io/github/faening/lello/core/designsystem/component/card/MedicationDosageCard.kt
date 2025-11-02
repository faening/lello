package io.github.faening.lello.core.designsystem.component.card

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.testing.data.MedicationDosageTestData
import kotlin.rem
import kotlin.text.toInt
import kotlin.toString

@Composable
fun LelloMedicationDosageCard(
    dosage: MedicationDosage,
    onRemove: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {

    @SuppressLint("DefaultLocale")
    val formattedTime = dosage.time.let { time ->
        String.format("%02dh %02dm", time.hour, time.minute)
    }

    val formattedQuantity = if (dosage.quantity % 1 == 0.0) {
        dosage.quantity.toInt().toString()
    } else {
        dosage.quantity.toString()
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
                    color = MedicationDosageCardDefaults.shadowColor(),
                    shape = LelloShape.cardShape
                )
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = LelloShape.cardShape,
            colors = MedicationDosageCardDefaults.containerColor(),
            elevation = MedicationDosageCardDefaults.elevation(),
            border = BorderStroke(
                width = Dimension.borderWidthThick,
                color = MedicationDosageCardDefaults.borderColor()
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimension.spacingRegular),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${dosage.dosageNumber}ª Dose",
                        style = MaterialTheme.typography.titleMedium,
                        color = MedicationDosageCardDefaults.primaryTextColor(),
                        maxLines = 1,
                        modifier = Modifier.padding(bottom = Dimension.spacingSmall)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Horário: ")
                            }
                            append(formattedTime)
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = MedicationDosageCardDefaults.secondaryTextColor(),
                        maxLines = 1,
                        modifier = Modifier.padding(bottom = Dimension.spacingSmall)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Quantidade: ")
                            }
                            append("$formattedQuantity ${dosage.unit}")
                        },
                        color = MedicationDosageCardDefaults.secondaryTextColor(),
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                    )
                }

                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = LelloIcons.Outlined.Trash.imageVector,
                        contentDescription = "Remover dosagem",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

private object MedicationDosageCardDefaults {
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
        return MaterialTheme.colorScheme.tertiary
    }

    @Composable
    fun secondaryTextColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }
}

// region: Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloMedicationDosageCardPreview_LightMode() {
    val dosage = MedicationDosageTestData.list.first()

    LelloTheme {
        LelloMedicationDosageCard(
            dosage = dosage,
            onRemove = {}
        )
    }
}

// endregion: Previews