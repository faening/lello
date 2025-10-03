package io.github.faening.lello.core.designsystem.component.appbar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloCalendarTopAppBar(
    selectedDate: LocalDate,
    navigateUp: TopAppBarAction? = null,
    actions: List<TopAppBarAction> = emptyList(),
    onDateSelected: (LocalDate) -> Unit,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        title = {
            CalendarTopAppBarTitle(
                selectedDate = selectedDate,
                moodColor = moodColor,
                colorScheme = colorScheme,
                onClick = { showDatePicker = true }
            )
        },
        navigationIcon = {
            TopAppBarNavigationIcon(
                navigateUp = navigateUp,
                moodColor = moodColor,
                colorScheme = colorScheme
            )
        },
        actions = {
            TopAppBarActionIcon(
                actions = actions,
                moodColor = moodColor,
                colorScheme = colorScheme
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TopAppBarProperties.backgroundColor(colorScheme, moodColor),
            titleContentColor = colorScheme.onBackground,
        )
    )

    CalendarTopAppBarDatePickerDialog(
        show = showDatePicker,
        initialDate = selectedDate,
        onDismiss = { showDatePicker = false },
        onDateSelected = onDateSelected
    )
}

@Composable
private fun CalendarTopAppBarTitle(
    selectedDate: LocalDate,
    moodColor: MoodColor,
    colorScheme: ColorScheme,
    onClick: () -> Unit
) {
    val isPreview = LocalInspectionMode.current

    Row(
        modifier = Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isPreview) Icons.Default.CalendarToday else LelloIcons.Outlined.Calendar.imageVector,
            contentDescription = "Selecionar data",
            tint = TopAppBarProperties.titleTextColor(colorScheme, moodColor)
        )
        Spacer(
            modifier = Modifier.width(Dimension.paddingComponentSmall)
        )
        Text(
            text = selectedDate.format(DateTimeFormatter.ofPattern(
                "'Hoje,' dd MMM",
                Locale("pt", "BR"))
            ),
            color = TopAppBarProperties.titleTextColor(colorScheme, moodColor),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(
            modifier = Modifier.width(Dimension.paddingComponentSmall)
        )
        Icon(
            imageVector = if (isPreview) Icons.Default.ArrowDropDown else LelloIcons.ChevronDown.imageVector,
            contentDescription = null,
            tint = TopAppBarProperties.titleTextColor(colorScheme, moodColor)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarTopAppBarDatePickerDialog(
    show: Boolean,
    initialDate: LocalDate,
    onDismiss: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    if (show) {
        val state = rememberDatePickerState(
            initialSelectedDateMillis = initialDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        )

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = state.selectedDateMillis
                        if (millis != null) {
                            val date = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            onDateSelected(date)
                        }
                        onDismiss()
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = state)
        }
    }
}

// region: Preview Light Theme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Primary",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloCalendarTopAppBar_LightTheme_Primary() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Secondary",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloCalendarTopAppBar_LightTheme_Secondary() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.SECONDARY
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Aquamarine",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloCalendarTopAppBar_LightTheme_Aquamarine() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.AQUAMARINE
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Blue",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloCalendarTopAppBar_LightTheme_Blue() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.BLUE
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Orange",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloCalendarTopAppBar_LightTheme_Orange() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.ORANGE
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Red",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloCalendarTopAppBar_LightTheme_Red() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.RED
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Inverse",
    group = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LelloCalendarTopAppBar_LightTheme_Inverse() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.INVERSE
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Dark Theme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Primary",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LelloCalendarTopAppBar_DarkTheme_Primary() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Secondary",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LelloCalendarTopAppBar_DarkTheme_Secondary() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.SECONDARY
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Aquamarine",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LelloCalendarTopAppBar_DarkTheme_Aquamarine() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.AQUAMARINE
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Blue",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LelloCalendarTopAppBar_DarkTheme_Blue() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.BLUE
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Orange",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LelloCalendarTopAppBar_DarkTheme_Orange() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.ORANGE
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Red",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LelloCalendarTopAppBar_DarkTheme_Red() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.RED
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Inverse",
    group = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LelloCalendarTopAppBar_DarkTheme_Inverse() {
    LelloTheme {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            navigateUp = TopAppBarAction(),
            actions = listOf(
                TopAppBarAction(
                    icon = LelloIcons.Favorite,
                    contentDescription = "Favoritos"
                ),
                TopAppBarAction(
                    icon = LelloIcons.MoreVert,
                    contentDescription = "Mais opções"
                )
            ),
            moodColor = MoodColor.INVERSE
        )
    }
}

// endregion: Preview Dark Theme