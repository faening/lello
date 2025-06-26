package io.github.faening.lello.core.designsystem.component

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import kotlin.math.roundToInt

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LelloSliderVertical(
    steps: Int,
    currentStep: Int,
    enableStepDrag: Boolean,
    onStepSelected: (Int) -> Unit,
) {
    val density = LocalDensity.current
    val thumbSize: Dp = 48.dp
    val trackWidth: Dp = 12.dp
    val baseModifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(vertical = 32.dp)
        .then(
            if (enableStepDrag) Modifier.pointerInput(steps, currentStep) {
                detectVerticalDragGestures { _, dragAmount ->
                    val nextIndex = when {
                        dragAmount < -10 -> currentStep - 1
                        dragAmount > 10 -> currentStep + 1
                        else -> currentStep
                    }.coerceIn(0, steps - 1)

                    if (nextIndex != currentStep) {
                        onStepSelected(nextIndex)
                    }
                }
            } else Modifier
        )

    BoxWithConstraints(
        modifier = baseModifier
    ) {
        val trackHeightPx = constraints.maxHeight.toFloat()
        val stepSpacing = with(density) { maxHeight.toPx() / (steps - 1) }
        val trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        val activeTrackColor = MaterialTheme.colorScheme.primary
        val trackWidthPx = with(density) { trackWidth.toPx() }

        Box {
            // Trilha do slider
            Canvas(modifier = Modifier.fillMaxSize()) {
                val trackX = size.width / 2 - trackWidthPx / 2

                drawRoundRect(
                    color = trackColor,
                    topLeft = Offset(trackX, 0f),
                    size = Size(trackWidthPx, size.height),
                    cornerRadius = CornerRadius(trackWidthPx / 2)
                )

                drawRoundRect(
                    color = activeTrackColor,
                    topLeft = Offset(trackX, stepSpacing * currentStep),
                    size = Size(trackWidthPx, size.height - stepSpacing * currentStep),
                    cornerRadius = CornerRadius(trackWidthPx / 2)
                )
            }

            // Thumb com ícone sobreposto
            val yOffset = (stepSpacing * currentStep).coerceIn(0f, trackHeightPx - with(density) { thumbSize.toPx() })

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = yOffset.roundToInt()
                        )
                    }
                    .size(thumbSize)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                    shadowElevation = 4.dp,
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxSize()
                ) {}

                Icon(
                    painter = painterResource(R.drawable.ic_fab_journal_mood),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(thumbSize / 2)
                )
            }
        }
    }
}

// region: Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Default",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    heightDp = 300,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloSliderVerticalDefaultLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.DEFAULT
    ) {
        LelloSliderVertical(
            steps = 5,
            currentStep = 1,
            enableStepDrag = true,
            onStepSelected = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Aquamarine",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    heightDp = 300,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloSliderVerticalAquamarineLightPreview() {
    LelloTheme(
        scheme = LelloColorScheme.AQUAMARINE
    ) {
        LelloSliderVertical(
            steps = 5,
            currentStep = 1,
            enableStepDrag = true,
            onStepSelected = {}
        )
    }
}

// endregion