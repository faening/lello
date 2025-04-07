package io.github.faening.lello.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        contentColor = NavigationDefaults.navigationSelectedItemColor(),
        containerColor = NavigationDefaults.navigationContainerColor(),
        tonalElevation = NavigationDefaults.navigationElevation,
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
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NavigationDefaults.navigationContentColor(),
            selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NavigationDefaults.navigationContentColor(),
            indicatorColor = NavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
private fun MobileNavigationBarContent() {
    val items = listOf("Home", "Diaries", "Achievements", "Dashboard", "Profile")
    val icons = listOf(
        LelloIcons.Home,
        LelloIcons.Diaries,
        LelloIcons.Achievements,
        LelloIcons.Dashboard,
        LelloIcons.Profile,
    )
    val selectedIcons = listOf(
        LelloIcons.Home,
        LelloIcons.Diaries,
        LelloIcons.Achievements,
        LelloIcons.Dashboard,
        LelloIcons.Profile,
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
                label = { Text(item) },
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
    fun navigationContentColor(): Color {
        // Use a cor onSurfaceVariant, que é adequada para conteúdo em áreas de superfície variante
        return MaterialTheme.colorScheme.onSurfaceVariant
    }

    /**
     * Cor para itens selecionados na barra de navegação
     */
    @Composable
    fun navigationSelectedItemColor(): Color {
        // Use a cor primária para destacar o item selecionado
        return MaterialTheme.colorScheme.primary
    }

    /**
     * Cor para o indicador na barra de navegação
     */
    @Composable
    fun navigationIndicatorColor(): Color {
        // Use o primaryContainer para o indicador, que é uma versão mais suave da cor primária
        return MaterialTheme.colorScheme.primaryContainer
    }

    /**
     * Cor de fundo para a barra de navegação
     */
    @Composable
    fun navigationContainerColor(): Color {
        // Use a cor de superfície, possivelmente com uma pequena elevação
        return MaterialTheme.colorScheme.surface
    }

    /**
     * Valor de elevação para a barra de navegação
     */
    val navigationElevation = 4.dp
}