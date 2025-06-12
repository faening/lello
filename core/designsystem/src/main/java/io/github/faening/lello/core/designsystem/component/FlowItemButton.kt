package io.github.faening.lello.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloFlowItemButton(
    onClick: () -> Unit,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
) {
    FloatingActionButton(
        modifier = Modifier.size(40.dp),
        containerColor = colorScheme.primary,
        shape = RoundedCornerShape(Dimension.Small),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = LelloIcons.Add,
            contentDescription = "Adicionar",
            tint = colorScheme.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Default",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFlowItemButtonDefaultLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloFlowItemButton(
            onClick = {}
        )
    }
}