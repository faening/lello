@file:Suppress("ConstPropertyName", "unused")

package io.github.faening.lello.core.designsystem.theme

import androidx.compose.ui.unit.dp

object Dimension {
    // ==================== ESPAÇAMENTOS BÁSICOS ====================
    val spacingExtraSmall = 4.dp
    val spacingSmall = 8.dp
    val spacingMedium = 12.dp
    val spacingRegular = 16.dp
    val spacingLarge = 24.dp
    val spacingExtraLarge = 32.dp
    val spacingHuge = 48.dp
    val spacingHero = 64.dp

    // ==================== PADDING E MARGIN ====================
    /** Padding horizontal padrão para telas */
    val paddingScreenHorizontal = spacingRegular

    /** Padding vertical padrão para telas */
    val paddingScreenVertical = spacingRegular

    /** Padding interno de componentes extremamente pequenos */
    val paddingComponentExtraSmall = spacingExtraSmall

    /** Padding interno de componentes pequenos */
    val paddingComponentSmall = spacingSmall

    /** Padding interno de componentes médios */
    val paddingComponentMedium = spacingMedium

    /** Margin entre cards e componentes grandes */
    val paddingComponentRegular = spacingRegular

    // ==================== BORDAS E CONTORNOS ====================
    /** Largura padrão de bordas finas */
    val borderWidthThin = 1.dp

    /** Largura padrão de bordas normais */
    val borderWidthDefault = 1.5.dp

    /** Largura de bordas espessas para destaque */
    val borderWidthThick = 2.dp

    /** Raio de borda pequeno para elementos menores */
    val borderRadiusSmall = 4.dp

    /** Raio de borda médio - padrão da aplicação */
    val borderRadiusMedium = 8.dp

    /** Raio de borda grande para elementos destacados */
    val borderRadiusLarge = 12.dp

    /** Raio para componentes totalmente arredondados */
    val borderRadiusRound = 24.dp

    // ==================== TRANSPARÊNCIAS E OPACIDADES ====================
    /** Opacidade para elementos desabilitados */
    const val alphaStateDisabled = 0.15f

    /** Opacidade para elementos em estado normal/não pressionado */
    const val alphaStateNormal = 0.3f

    /** Opacidade para elementos em estado pressionado */
    const val alphaStatePressed = 0.5f

    /** Opacidade para hover em elementos interativos */
    const val alphaStateHover = 0.07f

    /** Opacidade para overlay de fundo */
    const val alphaOverlayBackground = 0.6f

    // ==================== ALTURAS DE COMPONENTES ====================
    /** Altura para botões pequenos */
    val heightButtonSmall = 40.dp

    /** Altura padrão para botões */
    val heightButtonDefault = 56.dp

    /** Altura para botões grandes */
    val heightButtonLarge = 64.dp

    /** Altura para TextFields compactos */
    val heightTextFieldCompact = 48.dp

    /** Altura padrão para TextFields */
    val heightTextFieldDefault = 56.dp

    /** Altura mínima para itens de lista tocáveis */
    val heightListItemMin = 48.dp

    /** Altura padrão para itens de lista */
    val heightListItemDefault = 56.dp

    /** Altura para AppBar/TopBar */
    val heightAppBar = 64.dp

    /** Altura para BottomBar */
    val heightBottomBar = 80.dp

    // ==================== DIMENSÕES DE CARDS ====================
    /** Raio de borda para cards pequenos */
    val cardRadiusSmall = borderRadiusSmall

    /** Raio de borda para cards médios */
    val cardRadiusMedium = borderRadiusMedium

    /** Raio de borda para cards grandes */
    val cardRadiusLarge = borderRadiusLarge

    /** Largura da borda de cards */
    val cardBorderWidth = borderWidthDefault

    /** Padding interno padrão de cards */
    val cardPaddingDefault = spacingRegular

    // ==================== DIMENSÕES DE TEXTFIELD ====================
    /** Raio de borda para TextFields */
    val textFieldRadius = borderRadiusMedium

    /** Largura da borda de TextFields */
    val textFieldBorderWidth = borderWidthDefault

    /** Padding horizontal interno de TextFields */
    val textFieldPaddingHorizontal = spacingRegular

    /** Padding vertical interno de TextFields */
    val textFieldPaddingVertical = spacingMedium

    val textFieldMultilineHeight = 140.dp

    // ==================== DIMENSÕES DE ÍCONES ====================
    /** Tamanho de ícones pequenos */
    val iconSizeSmall = 18.dp

    /** Tamanho padrão de ícones */
    val iconSizeDefault = 24.dp

    /** Tamanho de ícones grandes */
    val iconSizeLarge = 32.dp

    /** Tamanho de ícones extra grandes */
    val iconSizeExtraLarge = 48.dp

    // ==================== SOMBRAS E ELEVAÇÃO ====================
    /** Offset horizontal para fake shadow */
    val shadowOffsetX = spacingSmall

    /** Offset vertical para fake shadow */
    val shadowOffsetY = spacingSmall

    /** Elevation baixa para componentes sutis */
    val elevation = 0.dp

    // ==================== LARGURAS E TAMANHOS ESPECÍFICOS ====================
    /** Largura mínima recomendada para botões */
    val widthButtonMin = 120.dp

    /** Largura máxima recomendada para botões */
    val widthButtonMax = 280.dp

    /** Largura máxima para TextFields em telas grandes */
    val widthTextFieldMax = 400.dp

    /** Tamanho de componentes quadrados pequenos */
    val sizeSquareSmall = 40.dp

    /** Tamanho de componentes quadrados médios */
    val sizeSquareMedium = 56.dp

    /** Tamanho de componentes quadrados grandes */
    val sizeSquareLarge = 72.dp
}