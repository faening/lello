package io.github.faening.lello.core.designsystem.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import kotlin.math.roundToInt

@Suppress("COMPOSE_APPLIER_CALL_MISMATCH")
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
    val trackPadding: Dp = 32.dp
    val baseModifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
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
        val thumbSizePx = with(density) { thumbSize.toPx() }
        val trackPaddingPx = with(density) { trackPadding.toPx() }
        val blockHeight = trackHeightPx / steps
        val trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        val activeTrackColor = MaterialTheme.colorScheme.primary
        val trackWidthPx = with(density) { trackWidth.toPx() }

        Box {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val trackX = size.width / 2 - trackWidthPx / 2
                val trackStartY = trackPaddingPx
                val trackEndY = size.height - trackPaddingPx
                val trackHeight = trackEndY - trackStartY

                drawRoundRect(
                    color = trackColor,
                    topLeft = Offset(trackX, trackStartY),
                    size = Size(trackWidthPx, trackHeight),
                    cornerRadius = CornerRadius(trackWidthPx / 2)
                )

                val activeStartY = (blockHeight * currentStep) + (blockHeight / 2)
                val activeHeight = (trackEndY - activeStartY).coerceAtLeast(0f)

                if (activeHeight > 0) {
                    drawRoundRect(
                        color = activeTrackColor,
                        topLeft = Offset(trackX, activeStartY),
                        size = Size(trackWidthPx, activeHeight),
                        cornerRadius = CornerRadius(trackWidthPx / 2)
                    )
                }
            }

            val yOffset = (blockHeight * currentStep + (blockHeight / 2) - (thumbSizePx / 2))
                .coerceIn(0f, trackHeightPx - thumbSizePx)

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
                    painter = painterResource(LelloIcons.Outlined.ChevronMirrored.resId),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(thumbSize / 2)
                )
            }
        }
    }
}