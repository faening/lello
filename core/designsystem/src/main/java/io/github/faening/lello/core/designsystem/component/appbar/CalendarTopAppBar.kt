package io.github.faening.lello.core.designsystem.component.appbar

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
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
    onDateSelected: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        title = {
            CalendarTopAppBarTitle(
                selectedDate = selectedDate,
                onClick = { showDatePicker = true }
            )
        },
        navigationIcon = {
            CalendarTopAppBarNavigationIcon(
                navigateUp = navigateUp
            )
        },
        actions = {
            CalendarTopAppBarActionIcon(
                actions = actions
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorScheme.primaryContainer,
            titleContentColor = colorScheme.onPrimaryContainer,
            navigationIconContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.primary
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
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CalendarToday,
            contentDescription = "Selecionar data"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = selectedDate.format(DateTimeFormatter.ofPattern("'Hoje,' dd MMM", Locale("pt", "BR"))),
            style = MaterialTheme.typography.titleMedium
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null
        )
    }
}

@Composable
private fun CalendarTopAppBarNavigationIcon(
    navigateUp: TopAppBarAction? = null
) {
    navigateUp?.let {
        Box(modifier = Modifier.padding(start = Dimension.Small)) {
            TopAppBarActionButton(
                action = it.also {
                    it.icon = LelloIcons.customIcon(R.drawable.ic_arrow_large_left)
                    it.contentDescription = "Voltar"
                },
                iconTint = colorScheme.onPrimary,
                background = colorScheme.primary
            )
        }
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

@Composable
private fun CalendarTopAppBarActionIcon(
    actions: List<TopAppBarAction> = emptyList()
) {
    actions.forEachIndexed { index, action ->
        Box(
            modifier = Modifier.padding(
                end = Dimension.Small
            )
        ) {
            TopAppBarActionButton(
                action = action,
                iconTint = colorScheme.onPrimary,
                background = colorScheme.primary
            )
        }
    }
}

// region: CalendarTopAppBar Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Default Color - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun CalendarTopAppBarPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloCalendarTopAppBar(
            selectedDate = LocalDate.now(),
            navigateUp = TopAppBarAction(),
            onDateSelected = {}
        )
    }
}

// endregion