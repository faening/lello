package io.github.faening.lello.core.designsystem.component.card

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloSettingsCard(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    items: List<SettingsItem>,
    colors: SettingsCardDefaults = SettingsCardDefaults
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Label
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = Dimension.spacingMedium)
        )

        Box(
            Modifier.padding(end = Dimension.spacingSmall, bottom = Dimension.spacingSmall)
        ) {
            // Fake Shadow
            Box(
                Modifier
                    .matchParentSize()
                    .offset(Dimension.spacingSmall, Dimension.spacingSmall)
                    .background(
                        color = colors.shadowColor(),
                        shape = LelloShape.cardShape
                    )
            )

            // Card Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colors.containerColor(),
                        shape = LelloShape.cardShape
                    )
                    .border(
                        width = Dimension.borderWidthThick,
                        color = colors.borderColor(),
                        shape = LelloShape.cardShape
                    )
                    .clip(LelloShape.cardShape)
                    .padding(horizontal = Dimension.spacingRegular)
            ) {
                items.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier
                            .height(96.dp)
                            .fillMaxWidth()
                            .clickable(enabled = item.onClick != null) { item.onClick?.invoke() }
                            .padding(vertical = Dimension.spacingRegular),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                    ) {
                        // Icon
                        Box(
                            modifier = Modifier
                                .size(Dimension.heightButtonDefault)
                                .background(
                                    color = colors.iconContainerColor(item.isDangerousTheme),
                                    shape = LelloShape.cardShape
                                )
                                .clip(LelloShape.cardShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = colors.iconColor(item.isDangerousTheme),
                            )
                        }

                        // Texts
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = item.title,
                                color = colors.primaryTextColor(),
                                maxLines = 1,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = item.subtitle,
                                color = colors.secondaryTextColor(),
                                maxLines = 2,
                                minLines = 1,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        when (item.type) {
                            SettingsItemType.NAVIGATION -> {
                                Icon(
                                    imageVector = LelloIcons.Outlined.ChevronRight.imageVector,
                                    contentDescription = null,
                                    tint = colors.iconColor(false),
                                )
                            }

                            SettingsItemType.SWITCH -> {
                                Switch(
                                    checked = item.isChecked ?: false,
                                    onCheckedChange = item.onCheckedChange,
                                    modifier = Modifier.scale(0.8f),
                                    colors = colors.switchColors()
                                )
                            }
                        }
                    }
                    if (index < items.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier,
                            thickness = DividerDefaults.Thickness,
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }
        }
    }
}

enum class SettingsItemType {
    NAVIGATION,
    SWITCH
}

data class SettingsItem(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val type: SettingsItemType,
    val onCheckedChange: ((Boolean) -> Unit)? = null,
    val onClick: (() -> Unit)? = null,
    val isChecked: Boolean? = null,
    val isDangerousTheme: Boolean = false,
)

object SettingsCardDefaults {
    @Composable
    fun borderColor(): Color {
        return MaterialTheme.colorScheme.outline
    }

    @Composable
    fun shadowColor(): Color {
        return MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal)
    }

    @Composable
    fun containerColor(): Color {
        return MaterialTheme.colorScheme.surfaceContainerLowest
    }

    @Composable
    fun primaryTextColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }

    @Composable
    fun secondaryTextColor(): Color {
        return MaterialTheme.colorScheme.onPrimaryContainer
    }

    @Composable
    fun iconContainerColor(isDangerousTheme: Boolean): Color {
        return when {
            isDangerousTheme -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.primary
        }
    }

    @Composable
    fun iconColor(isDangerousTheme: Boolean): Color {
        return when {
            isDangerousTheme -> MaterialTheme.colorScheme.onError
            else -> MaterialTheme.colorScheme.onPrimary
        }
    }

    @Composable
    fun switchColors(): SwitchColors {
        return SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = MaterialTheme.colorScheme.primary,
            checkedBorderColor = Color.Transparent,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            uncheckedBorderColor = Color.Transparent,
        )
    }
}

// region Previews

@Composable
@Preview(
    name = "Multiple Items",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloSettingsCardPreview_LightMode_MultipleItems() {
    LelloTheme {
        LelloSettingsCard(
            modifier = Modifier.padding(Dimension.spacingRegular),
            sectionTitle = "Segurança e Privacidade",
            items = listOf(
                SettingsItem(
                    icon = LelloIcons.Outlined.Sum.imageVector,
                    title = "Personalização",
                    subtitle = "Ative ou desative o tema escuro em seu aparelho",
                    type = SettingsItemType.SWITCH,
                    isChecked = true,
                    onCheckedChange = {}
                ),
                SettingsItem(
                    icon = LelloIcons.Outlined.NotificationBell.imageVector,
                    title = "Notificações",
                    subtitle = "Gerencie suas notificações",
                    type = SettingsItemType.NAVIGATION,
                    onClick = {}
                ),
                SettingsItem(
                    icon = LelloIcons.Outlined.Document.imageVector,
                    title = "Exportar dados em PDF",
                    subtitle = "Compartilhe seus diários",
                    type = SettingsItemType.NAVIGATION,
                    onClick = {}
                )
            )
        )
    }
}

@Composable
@Preview(
    name = "Dangerous Item",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloSettingsCardPreview_LightMode_DangerousItem() {
    LelloTheme {
        LelloSettingsCard(
            modifier = Modifier.padding(Dimension.spacingRegular),
            sectionTitle = "Conta",
            items = listOf(
                SettingsItem(
                    icon = LelloIcons.Outlined.Trash.imageVector,
                    title = "Excluir conta",
                    subtitle = "Excluir permanentemente sua conta e todos os dados associados",
                    type = SettingsItemType.NAVIGATION,
                    isDangerousTheme = true
                )
            )
        )
    }
}

// region Previews