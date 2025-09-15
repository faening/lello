package io.github.faening.lello.core.designsystem.theme

import android.annotation.SuppressLint
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.faening.lello.core.designsystem.R

private val LelloFontFamily = FontFamily(
    fonts = listOf(
        Font(resId = R.font.uniform_rounded_light, weight = FontWeight.Light),
        Font(resId = R.font.uniform_rounded, weight = FontWeight.Normal),
        Font(resId = R.font.uniform_rounded_medium, weight = FontWeight.Medium),
        Font(resId = R.font.uniform_rounded_bold, weight = FontWeight.Bold),
        Font(resId = R.font.uniform_rounded_black, weight = FontWeight.Black)
    )
)

@SuppressLint("ComposableNaming")
@Composable
fun LelloTypography(): Typography {
    val textColor = Grey500
    return Typography(
        displayLarge = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp,
            color = textColor
        ),

        displayMedium = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),

        displaySmall = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),

        headlineLarge = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),

        headlineMedium = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),

        headlineSmall = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),

        titleLarge = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),

        titleMedium = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            color = textColor
        ),

        titleSmall = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            color = textColor
        ),

        bodyLarge = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
            color = textColor
        ),

        bodyMedium = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = textColor
        ),

        bodySmall = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            color = textColor
        ),

        labelLarge = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            color = textColor
        ),

        labelMedium = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            color = textColor
        ),

        labelSmall = TextStyle(
            fontFamily = LelloFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            color = textColor
        )
    )
}