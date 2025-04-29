@file:Suppress("unused")

package io.github.faening.lello.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        contentColor = NavigationDefaults.selectedItemColor(),
        containerColor = NavigationDefaults.containerColor(),
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
    val selectedColor = NavigationDefaults.selectedItemColor()
    val unselectedColor = NavigationDefaults.unselectedItemColor()

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label?.let { labelContent ->
            {
                val textStyle = if (selected) {
                    MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = selectedColor,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                    )
                } else {
                    MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = unselectedColor,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
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
            indicatorColor = NavigationDefaults.indicatorColor(),
        ),
    )
}

@Composable
private fun MobileNavigationBarContent() {
    val items = listOf("Início", "Diários", "Conquistas", "Dashboards", "Perfil")
    val icons = listOf(
        LelloIcons.Home,
        LelloIcons.Diary,
        LelloIcons.Achievements,
        LelloIcons.Dashboard,
        LelloIcons.Profile,
    )
    val selectedIcons = listOf(
        LelloIcons.HomeBorder,
        LelloIcons.DiaryBorder,
        LelloIcons.AchievementsBorder,
        LelloIcons.DashboardBorder,
        LelloIcons.ProfileBorder,
    )

    LelloNavigationBar {
        items.forEachIndexed { index, item ->
            LelloNavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = selectedIcons[index],
                        contentDescription = item,
                    )
                },
                label = {
                    Text(
                        text = item,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                selected = index == 0,
                onClick = { },
            )
        }
    }
}

@Preview(name = "Light Theme", showBackground = true)
@Composable
private fun NavigationBarLightPreview() {
    LelloTheme(darkTheme = false) {
        MobileNavigationBarContent()
    }
}

@Preview(name = "Dark Theme", showBackground = true)
@Composable
private fun NavigationBarDarkPreview() {
    LelloTheme(darkTheme = true) {
        MobileNavigationBarContent()
    }
}

private object NavigationDefaults {
    /**
     * Cor para itens não selecionados na barra de navegação
     */
    @Composable
    fun contentColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }

    /**
     * Cor para itens não selecionados na barra de navegação (Grey500)
     */
    @Composable
    fun unselectedItemColor(): Color {
        return MaterialTheme.colorScheme.onPrimaryContainer
    }

    /**
     * Cor para itens selecionados na barra de navegação
     */
    @Composable
    fun selectedItemColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
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
        return MaterialTheme.colorScheme.surface
    }
}