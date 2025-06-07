package io.github.faening.lello.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloSelectablePill(
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit,
    shapeRadius: Dp = 4.dp,
    horizontalPadding: Dp = 20.dp,
    verticalPadding: Dp = 16.dp
) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(shapeRadius),
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
        contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.Normal,
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Unselected",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloSelectablePillUnselectedLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloSelectablePill(
            label = "Feliz",
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Dark",
    group = "Unselected",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloSelectablePillUnselectedDarkPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloSelectablePill(
            label = "Feliz",
            onClick = {},
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
private fun LelloSelectablePillDefaultLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Dark",
    group = "Default",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloSelectablePillDefaultDarkPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Aquamarine",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloSelectablePillAquamarineLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.AQUAMARINE
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Dark",
    group = "Aquamarine",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloSelectablePillAquamarineDarkPreview() {
    LelloTheme(
        scheme = LelloColorScheme.AQUAMARINE
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Blue",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloSelectablePillBlueLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.BLUE
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Dark",
    group = "Default",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloSelectablePillBlueDarkPreview() {
    LelloTheme(
        scheme = LelloColorScheme.BLUE
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Orange",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloSelectablePillOrangeLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.ORANGE
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Dark",
    group = "Orange",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloSelectablePillOrangeDarkPreview() {
    LelloTheme(
        scheme = LelloColorScheme.ORANGE
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Red",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloSelectablePillRedLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.RED
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Dark",
    group = "Red",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloSelectablePillRedDarkPreview() {
    LelloTheme(
        scheme = LelloColorScheme.RED
    ) {
        LelloSelectablePill(
            label = "Feliz",
            selected = true,
            onClick = {},
        )
    }
}
