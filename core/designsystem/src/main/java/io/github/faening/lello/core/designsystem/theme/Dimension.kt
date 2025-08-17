package io.github.faening.lello.core.designsystem.theme

import androidx.compose.ui.unit.dp

object Dimension {
    // ==================== ESPAÇAMENTOS BÁSICOS ====================
    val SPACING_MINIMAL = 4.dp
    val SPACING_COMPACT = 8.dp
    val SPACING_COMFORTABLE = 12.dp
    val SPACING_STANDARD = 16.dp
    val SPACING_GENEROUS = 24.dp
    val SPACING_SPACIOUS = 32.dp
    val SPACING_EXPANSIVE = 48.dp
    val SPACING_MONUMENTAL = 64.dp

    // ==================== PADDING E MARGIN ====================
    /** Padding horizontal padrão para telas */
    val PADDING_SCREEN_HORIZONTAL = SPACING_STANDARD

    /** Padding vertical padrão para telas */
    val PADDING_SCREEN_VERTICAL = SPACING_STANDARD

    /** Padding interno de componentes pequenos */
    val PADDING_COMPONENT_SMALL = SPACING_COMPACT

    /** Padding interno de componentes médios */
    val PADDING_COMPONENT_MEDIUM = SPACING_STANDARD

    /** Margin entre cards e componentes grandes */
    val MARGIN_COMPONENT_DEFAULT = SPACING_STANDARD

    // ==================== BORDAS E CONTORNOS ====================
    /** Largura padrão de bordas finas */
    val BORDER_WIDTH_THIN = 1.dp

    /** Largura padrão de bordas normais */
    val BORDER_WIDTH_DEFAULT = 2.dp

    /** Largura de bordas espessas para destaque */
    val BORDER_WIDTH_THICK = 3.dp

    /** Raio de borda pequeno para elementos menores */
    val BORDER_RADIUS_SMALL = 4.dp

    /** Raio de borda médio - padrão da aplicação */
    val BORDER_RADIUS_MEDIUM = 8.dp

    /** Raio de borda grande para elementos destacados */
    val BORDER_RADIUS_LARGE = 12.dp

    /** Raio para componentes totalmente arredondados */
    val BORDER_RADIUS_ROUND = 24.dp

    // ==================== TRANSPARÊNCIAS E OPACIDADES ====================
    /** Opacidade para elementos em estado normal/não pressionado */
    const val ALPHA_STATE_NORMAL = 0.25f

    /** Opacidade para elementos em estado pressionado */
    const val ALPHA_STATE_PRESSED = 0.35f

    /** Opacidade para elementos desabilitados */
    const val ALPHA_STATE_DISABLED = 0.15f

    /** Opacidade para overlay de fundo */
    const val ALPHA_OVERLAY_BACKGROUND = 0.6f

    /** Opacidade para hover em elementos interativos */
    const val ALPHA_STATE_HOVER = 0.08f

    // ==================== ALTURAS DE COMPONENTES ====================
    /** Altura padrão para botões */
    val HEIGHT_BUTTON_DEFAULT = 56.dp

    /** Altura para botões pequenos */
    val HEIGHT_BUTTON_SMALL = 40.dp

    /** Altura para botões grandes */
    val HEIGHT_BUTTON_LARGE = 64.dp

    /** Altura padrão para TextFields */
    val HEIGHT_TEXT_FIELD_DEFAULT = 56.dp

    /** Altura para TextFields compactos */
    val HEIGHT_TEXT_FIELD_COMPACT = 48.dp

    /** Altura mínima para itens de lista tocáveis */
    val HEIGHT_LIST_ITEM_MIN = 48.dp

    /** Altura padrão para itens de lista */
    val HEIGHT_LIST_ITEM_DEFAULT = 56.dp

    /** Altura para AppBar/TopBar */
    val HEIGHT_APP_BAR = 64.dp

    /** Altura para BottomBar */
    val HEIGHT_BOTTOM_BAR = 80.dp

    // ==================== DIMENSÕES DE CARDS ====================
    /** Raio de borda para cards pequenos */
    val CARD_RADIUS_SMALL = BORDER_RADIUS_SMALL

    /** Raio de borda para cards médios */
    val CARD_RADIUS_MEDIUM = BORDER_RADIUS_MEDIUM

    /** Raio de borda para cards grandes */
    val CARD_RADIUS_LARGE = BORDER_RADIUS_LARGE

    /** Largura da borda de cards */
    val CARD_BORDER_WIDTH = BORDER_WIDTH_DEFAULT

    /** Padding interno padrão de cards */
    val CARD_PADDING_DEFAULT = SPACING_STANDARD

    /** Elevation padrão para cards */
    val CARD_ELEVATION_DEFAULT = 4.dp

    // ==================== DIMENSÕES DE TEXTFIELD ====================
    /** Raio de borda para TextFields */
    val TEXT_FIELD_RADIUS = BORDER_RADIUS_MEDIUM

    /** Largura da borda de TextFields */
    val TEXT_FIELD_BORDER_WIDTH = BORDER_WIDTH_DEFAULT

    /** Padding horizontal interno de TextFields */
    val TEXT_FIELD_PADDING_HORIZONTAL = SPACING_STANDARD

    /** Padding vertical interno de TextFields */
    val TEXT_FIELD_PADDING_VERTICAL = SPACING_COMFORTABLE

    // ==================== DIMENSÕES DE ÍCONES ====================
    /** Tamanho de ícones pequenos */
    val ICON_SIZE_SMALL = 16.dp

    /** Tamanho padrão de ícones */
    val ICON_SIZE_DEFAULT = 24.dp

    /** Tamanho de ícones grandes */
    val ICON_SIZE_LARGE = 32.dp

    /** Tamanho de ícones extra grandes */
    val ICON_SIZE_EXTRA_LARGE = 48.dp

    // ==================== SOMBRAS E ELEVAÇÃO ====================
    /** Offset horizontal para fake shadow */
    val SHADOW_OFFSET_X = SPACING_COMPACT

    /** Offset vertical para fake shadow */
    val SHADOW_OFFSET_Y = SPACING_COMPACT

    /** Elevation baixa para componentes sutis */
    val ELEVATION_LOW = 2.dp

    /** Elevation média para componentes normais */
    val ELEVATION_MEDIUM = 4.dp

    /** Elevation alta para componentes destacados */
    val ELEVATION_HIGH = 8.dp

    /** Elevation máxima para modais e dialogs */
    val ELEVATION_MODAL = 16.dp

    // ==================== LARGURAS E TAMANHOS ESPECÍFICOS ====================
    /** Largura mínima recomendada para botões */
    val WIDTH_BUTTON_MIN = 120.dp

    /** Largura máxima recomendada para botões */
    val WIDTH_BUTTON_MAX = 280.dp

    /** Largura máxima para TextFields em telas grandes */
    val WIDTH_TEXT_FIELD_MAX = 400.dp

    /** Tamanho de componentes quadrados pequenos */
    val SIZE_SQUARE_SMALL = 40.dp

    /** Tamanho de componentes quadrados médios */
    val SIZE_SQUARE_MEDIUM = 56.dp

    /** Tamanho de componentes quadrados grandes */
    val SIZE_SQUARE_LARGE = 72.dp





    val ExtraSmall = 4.dp
    val Small = 8.dp
    val Medium = 16.dp
    val Large = 24.dp
    val ExtraLarge = 32.dp
    val Huge = 48.dp


    // Drop shadow offset
    const val ALPHA_UNPRESSED = 0.25f
    const val ALPHA_PRESSED = 0.35f
    const val ALPHA_DISABLED = 0.15f


    // Buttons
    val buttonHeight = 56.dp

    // Card
    val cardBorderStrokeWidth = 2.dp
    val cardRadiusSmall = 8.dp
    val cardRadiusMedium = 12.dp
    val cardRadiusLarge = 16.dp

    // TextField
    val textFieldHeight = 56.dp
    val textFieldRadius = 12.dp
}