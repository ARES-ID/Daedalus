package com.rjspies.daedalus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rjspies.daedalus.R

private val Medium = FontFamily(Font(R.font.poppins_medium, weight = FontWeight.Medium))
private val Regular = FontFamily(Font(R.font.poppins_regular, weight = FontWeight.Normal))

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Regular,
        lineHeight = 64.sp,
        fontSize = 57.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight(400)
    ),
    displayMedium = TextStyle(
        fontFamily = Regular,
        lineHeight = 52.sp,
        fontSize = 45.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight(400)
    ),
    displaySmall = TextStyle(
        fontFamily = Regular,
        lineHeight = 44.sp,
        fontSize = 36.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight(400)
    ),
    headlineLarge = TextStyle(
        fontFamily = Regular,
        lineHeight = 40.sp,
        fontSize = 32.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight(400)
    ),
    headlineMedium = TextStyle(
        fontFamily = Regular,
        lineHeight = 36.sp,
        fontSize = 28.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight(400)
    ),
    headlineSmall = TextStyle(
        fontFamily = Regular,
        lineHeight = 32.sp,
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight(400)
    ),
    titleLarge = TextStyle(
        fontFamily = Regular,
        lineHeight = 28.sp,
        fontSize = 22.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight(400)
    ),
    titleMedium = TextStyle(
        fontFamily = Medium,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
        fontWeight = FontWeight(500)
    ),
    titleSmall = TextStyle(
        fontFamily = Medium,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp,
        fontWeight = FontWeight(500)
    ),
    labelLarge = TextStyle(
        fontFamily = Medium,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp,
        fontWeight = FontWeight(500)
    ),
    labelMedium = TextStyle(
        fontFamily = Medium,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight(500)
    ),
    labelSmall = TextStyle(
        fontFamily = Medium,
        lineHeight = 16.sp,
        fontSize = 11.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight(500)
    ),
    bodyLarge = TextStyle(
        fontFamily = Medium,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight(400)
    ),
    bodyMedium = TextStyle(
        fontFamily = Regular,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        fontWeight = FontWeight(400)
    ),
    bodySmall = TextStyle(
        fontFamily = Regular,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
        fontWeight = FontWeight(400)
    )
)
