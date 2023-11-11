package com.rjspies.daedalus.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rjspies.daedalus.ui.theme.Typography
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

public class TypographyTest {
    private val medium = FontFamily(Font(R.font.poppins_medium, weight = FontWeight.Medium))
    private val regular = FontFamily(Font(R.font.poppins_regular, weight = FontWeight.Normal))

    private val displayLarge by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 64.sp,
            fontSize = 57.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val displayMedium by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 52.sp,
            fontSize = 45.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val displaySmall by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 44.sp,
            fontSize = 36.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val headlineLarge by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 40.sp,
            fontSize = 32.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val headlineMedium by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 36.sp,
            fontSize = 28.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val headlineSmall by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 32.sp,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val titleLarge by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 28.sp,
            fontSize = 22.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val titleMedium by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 24.sp,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
            fontWeight = FontWeight(500),
        )
    }

    private val titleSmall by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight(500),
        )
    }

    private val labelLarge by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight(500),
        )
    }

    private val labelMedium by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 16.sp,
            fontSize = 12.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight(500),
        )
    }

    private val labelSmall by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 16.sp,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight(500),
        )
    }

    private val bodyLarge by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 24.sp,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val bodyMedium by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
            fontWeight = FontWeight(400),
        )
    }

    private val bodySmall by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 16.sp,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp,
            fontWeight = FontWeight(400),
        )
    }

    @Test
    fun displayMedium() {

    }

    @Test
    fun `Text style configuration`() {
        assertEquals(displayLarge, Typography.displayLarge)
        assertEquals(displayMedium, Typography.displayMedium)
        assertEquals(displaySmall, Typography.displaySmall)
        assertEquals(headlineLarge, Typography.headlineLarge)
        assertEquals(headlineMedium, Typography.headlineMedium)
        assertEquals(headlineSmall, Typography.headlineSmall)
        assertEquals(titleLarge, Typography.titleLarge)
        assertEquals(titleMedium, Typography.titleMedium)
        assertEquals(titleSmall, Typography.titleSmall)
        assertEquals(labelLarge, Typography.labelLarge)
        assertEquals(labelMedium, Typography.labelMedium)
        assertEquals(labelSmall, Typography.labelSmall)
        assertEquals(bodyLarge, Typography.bodyLarge)
        assertEquals(bodyMedium, Typography.bodyMedium)
        assertEquals(bodySmall, Typography.bodySmall)
    }
}
