package io.github.faening.lello.core.designsystem.component.card

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.testing.data.MedicationTestData

@Composable
fun LelloMedicationCard(
    medication: Medication,
    onDosageClick: (Int) -> Unit = {},
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
                    color = MedicationCardDefaults.shadowColor(),
                    shape = LelloShape.cardShape
                )
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = LelloShape.cardShape,
            colors = MedicationCardDefaults.containerColor(),
            elevation = MedicationCardDefaults.elevation(),
            border = BorderStroke(
                width = Dimension.borderWidthThick,
                color = MedicationCardDefaults.borderColor()
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimension.spacingMedium)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // Icon
                    Box(
                        modifier = Modifier
                            .background(
                                color = MedicationCardDefaults.pillIconBackgroundColor(),
                                shape = LelloShape.cardShape
                            )
                            .size(Dimension.heightButtonSmall)
                            .padding(Dimension.spacingSmall),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = getMedicationTypeIcon(medication.dosageFormOption),
                            contentDescription = medication.dosageFormOption?.description ?: "Ícone de medicação",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(Dimension.iconSizeSmall)
                        )
                    }
                    Spacer(modifier = Modifier.width(Dimension.spacingMedium))

                    // Description (Active Ingredient)
                    Text(
                        text = medication.activeIngredientOption?.description?.uppercase() ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        color = MedicationCardDefaults.primaryTextColor(),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = Dimension.spacingRegular),
                    thickness = Dimension.borderWidthDefault,
                    color = MedicationCardDefaults.dividerColor()
                )

                medication.dosages.forEachIndexed { index, dosage ->
                    DosageItem(
                        dosageNumber = index + 1,
                        dosage = dosage,
                        onClick = { onDosageClick(index) }
                    )

                    if (index < medication.dosages.size - 1) {
                        Spacer(modifier = Modifier.height(Dimension.spacingMedium))
                    }
                }
            }
        }
    }
}

@Composable
private fun getMedicationTypeIcon(
    form: MedicationDosageFormOption?
): ImageVector {
    return when (form?.description?.lowercase()) {
        "comprimido" -> LelloIcons.Outlined.DrugPill.imageVector
        else -> LelloIcons.Outlined.DrugPill.imageVector
    }
}

@Composable
private fun DosageItem(
    dosageNumber: Int,
    dosage: MedicationDosage,
    onClick: () -> Unit
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

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = MedicationCardDefaults.containerColor(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimension.spacingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Dosage Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${dosageNumber}º Dose",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MedicationCardDefaults.secondaryTextColor(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimension.spacingSmall)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Horário: ")
                        }
                        append(formattedTime)
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MedicationCardDefaults.primaryTextColor(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimension.spacingSmall)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Quantidade: ")
                        }
                        append("$formattedQuantity ${dosage.unitOption?.description?.lowercase()}")
                    },
                    color = MedicationCardDefaults.primaryTextColor(),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            // Arrow Icon
            Spacer(modifier = Modifier.width(Dimension.spacingMedium))
            Icon(
                imageVector = LelloIcons.Outlined.ChevronRight.imageVector,
                contentDescription = "Editar dose",
                tint = MedicationCardDefaults.dividerColor(),
                modifier = Modifier.size(Dimension.iconSizeDefault)
            )
        }
    }
}



private object MedicationCardDefaults {
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
    fun dividerColor(): Color {
        return MaterialTheme.colorScheme.outlineVariant
    }

    @Composable
    fun pillIconBackgroundColor(): Color {
        return MaterialTheme.colorScheme.primary
    }

    @Composable
    fun primaryTextColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }

    @Composable
    fun secondaryTextColor(): Color {
        return MaterialTheme.colorScheme.tertiary
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
private fun LelloMedicationCardPreview_LightMode() {
    val medication = MedicationTestData.list[1]

    LelloTheme {
        LelloMedicationCard(
            medication = medication,
            modifier = Modifier.padding(Dimension.spacingSmall)
        )
    }
}

// endregion: Previews