package io.github.faening.lello.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val LelloShapes = Shapes(
    // Formas pequenas - botões pequenos, chips, badges
    small = RoundedCornerShape(Dimension.borderRadiusSmall),

    // Formas médias - botões, cards, text fields
    medium = RoundedCornerShape(Dimension.borderRadiusMedium),

    // Formas grandes - dialogs, bottom sheets, grandes containers
    large = RoundedCornerShape(Dimension.borderRadiusLarge),

    // Formato extra grande - telas completas, modais
    extraLarge = RoundedCornerShape(Dimension.borderRadiusRound)
)

object LelloShape {
    // Buttons
    val buttonShape = RoundedCornerShape(Dimension.borderRadiusMedium)
    val fabShape = RoundedCornerShape(Dimension.borderRadiusMedium)
    val pillShape = RoundedCornerShape(Dimension.borderRadiusMedium)

    // AppBars
    val topAppBarShape = RoundedCornerShape(bottomStart = Dimension.borderRadiusLarge, bottomEnd = Dimension.borderRadiusLarge)
    val navigationBarShape = RoundedCornerShape(topStart = Dimension.borderRadiusLarge, topEnd = Dimension.borderRadiusLarge)
    val navigationBarItemShape = RoundedCornerShape(Dimension.borderRadiusRound)

    // Other
    val textFieldShape = RoundedCornerShape(Dimension.borderRadiusMedium)
    val cardShape = RoundedCornerShape(Dimension.borderRadiusLarge)
    val bottomSheetShape = RoundedCornerShape(
        topStart = Dimension.borderRadiusLarge,
        topEnd = Dimension.borderRadiusLarge,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )
}