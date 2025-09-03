package io.github.faening.lello.core.designsystem.component.navigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Grey300
import io.github.faening.lello.core.designsystem.theme.Grey700
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        contentColor = NavigationProperties.selectedItemColor(),
        containerColor = NavigationProperties.containerColor(),
        content = content,
    )
}

@Composable
fun RowScope.LelloNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    val selectedColor = NavigationProperties.selectedItemColor()
    val unselectedColor = NavigationProperties.unselectedItemColor()

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label?.let { labelContent ->
            {
                val textStyle = if (selected) {
                    MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = selectedColor,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                } else {
                    MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = unselectedColor,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                }
                CompositionLocalProvider(
                    LocalTextStyle provides textStyle
                ) {
                    labelContent()
                }
            }
        },
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = selectedColor,
            unselectedIconColor = unselectedColor,
            selectedTextColor = selectedColor,
            unselectedTextColor = unselectedColor,
            indicatorColor = NavigationProperties.indicatorColor(),
        ),
    )
}

@Composable
fun CentralNavigationBarItem(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = Dimension.paddingComponentExtraSmall)
            .height(Dimension.heightButtonDefault)
            .width(Dimension.heightButtonDefault)
            .wrapContentSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = LelloShape.navigationBarShape
                )
                .padding(horizontal = Dimension.paddingComponentMedium, vertical = Dimension.paddingComponentSmall)
                .clickable(
                    onClick = { onClick() }
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(start = Dimension.paddingComponentExtraSmall),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Achievements",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(Dimension.heightButtonSmall)
                )
            }
        }
    }
}

private object NavigationProperties {
    /**
     * Cor para itens não selecionados na barra de navegação
     */
    @Composable
    fun contentColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }

    /**
     * Cor para itens não selecionados na barra de navegação
     */
    @Composable
    fun unselectedItemColor(): Color {
        return MaterialTheme.colorScheme.secondaryContainer
    }

    /**
     * Cor para itens selecionados na barra de navegação
     */
    @Composable
    fun selectedItemColor(): Color {
        return MaterialTheme.colorScheme.secondary
    }

    /**
     * Cor para o indicador na barra de navegação
     */
    @Composable
    fun indicatorColor(): Color {
        return MaterialTheme.colorScheme.primary
    }

    /**
     * Cor de fundo para a barra de navegação
     */
    @Composable
    fun containerColor(): Color {
        return MaterialTheme.colorScheme.background
    }
}

@Composable
private fun MobileNavigationBarContent() {
    val items = listOf("Início", "Diários", "Lello", "Remédios", "Perfil")
    val icons = listOf(
        LelloIcons.Outlined.Home.imageVector,
        LelloIcons.Outlined.BookOpen.imageVector,
        LelloIcons.Outlined.Achievement.imageVector,
        LelloIcons.Outlined.DrugPill.imageVector,
        LelloIcons.Outlined.Profile.imageVector
    )
    val selectedIcons = listOf(
        LelloIcons.Filled.Home.imageVector,
        LelloIcons.Filled.BookOpen.imageVector,
        LelloIcons.Filled.Achievement.imageVector,
        LelloIcons.Filled.DrugPill.imageVector,
        LelloIcons.Filled.Profile.imageVector
    )

    LelloNavigationBar {
        items.forEachIndexed { index, item ->
            // Achievements Button
            if (index == 2) {
                CentralNavigationBarItem(
                    icon = icons[index],
                    onClick = {}
                )
            } else {
                LelloNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                            tint = Grey300
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                            tint = Grey700
                        )
                    },
                    label = {
                        Text(
                            text = item,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Grey300
                        )
                    },
                    selected = index == 0,
                    onClick = { /* ação */ },
                )
            }
        }
    }
}

// region: Preview

@Preview(
    name = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun NavigationBar_LightTheme() {
    LelloTheme {
        MobileNavigationBarContent()
    }
}

@Preview(
    name = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun NavigationBar_DarkTheme() {
    LelloTheme {
        MobileNavigationBarContent()
    }
}

// endregion: Preview